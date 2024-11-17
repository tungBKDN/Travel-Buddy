package com.travelbuddy.site.user;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SiteVersionSpecifications {

    public Specification<SiteVersionEntity> customSearchAndLatestApproved(String search) {
        // Đặt các hệ số cho thuật toán
        double a = 1.0; // Hệ số cho số sao trung bình
        double b = 0.5; // Hệ số cho số lượng review
        double c = 0.2; // Hệ số cho số lượng "like"
        double d = -0.3; // Hệ số cho số lượng "dislike"
        double e = -0.1; // Hệ số cho khoảng cách ngày tạo

        return (root, query, criteriaBuilder) -> {
            // Tham gia bảng liên quan
            var siteEntityJoin = root.join("siteEntity", JoinType.INNER);
            var reviewJoin = siteEntityJoin.join("siteReviewEntities", JoinType.LEFT);
            var reactionJoin = reviewJoin.join("reviewReactions", JoinType.LEFT);

            // Điều kiện chỉ lấy các siteVersion đã được duyệt
            Predicate approvedPredicate = criteriaBuilder.equal(
                    root.get("siteApprovalEntity").get("status"), ApprovalStatusEnum.APPROVED
            );

            // Điều kiện lấy phiên bản mới nhất (latest) dựa trên `createdAt`
            assert query != null;
            Subquery<LocalDateTime> latestApprovedSubquery = query.subquery(LocalDateTime.class);
            var subRoot = latestApprovedSubquery.from(SiteVersionEntity.class);
            latestApprovedSubquery.select(
                    criteriaBuilder.function("MAX", LocalDateTime.class, subRoot.get("createdAt"))
            ).where(
                    criteriaBuilder.equal(subRoot.get("siteEntity").get("id"), root.get("siteEntity").get("id")),
                    criteriaBuilder.equal(subRoot.get("siteApprovalEntity").get("status"), ApprovalStatusEnum.APPROVED)
            );

            Predicate latestVersionPredicate = criteriaBuilder.equal(root.get("createdAt"), latestApprovedSubquery);

            // Điều kiện tìm kiếm theo `siteName` hoặc `resolvedAddress`
            Predicate searchPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(
                            criteriaBuilder.function("unaccent", String.class, criteriaBuilder.lower(root.get("siteName"))),
                            "%" + removeAccents(search.toLowerCase()) + "%"
                    ),
                    criteriaBuilder.like(
                            criteriaBuilder.function("unaccent", String.class, criteriaBuilder.lower(root.get("resolvedAddress"))),
                            "%" + removeAccents(search.toLowerCase()) + "%"
                    )
            );

            // Biểu thức "priority" để ưu tiên kết quả khớp với tìm kiếm
            Expression<Integer> priority = criteriaBuilder.<Integer>selectCase()
                    .when(searchPredicate, 1) // Kết quả khớp với điều kiện tìm kiếm
                    .otherwise(0); // Không khớp

            // Tính toán số sao trung bình
            Expression<Double> averageRating = criteriaBuilder.avg(reviewJoin.get("generalRating"));

            // Tính toán số lượng review
            Expression<Long> reviewCount = criteriaBuilder.count(reviewJoin);

            // Tính toán số lượng "like" và "dislike"
            Expression<Long> likeCount = criteriaBuilder.count(
                    criteriaBuilder.equal(reactionJoin.get("reactionType"), "LIKE")
            );
            Expression<Long> dislikeCount = criteriaBuilder.count(
                    criteriaBuilder.equal(reactionJoin.get("reactionType"), "DISLIKE")
            );

            // Tính toán khoảng cách ngày tạo đến hiện tại
            Expression<Number> intervalInDays = criteriaBuilder.function(
                    "DATE_PART", Number.class,
                    criteriaBuilder.literal("day"),
                    criteriaBuilder.function("AGE", Date.class, criteriaBuilder.currentTimestamp(), root.get("createdAt"))
            );

            // Tính toán điểm số tùy chỉnh
            Expression<Double> customScore = criteriaBuilder.sum(
                    criteriaBuilder.sum(
                            criteriaBuilder.prod(a, averageRating),
                            criteriaBuilder.prod(b, criteriaBuilder.toDouble(reviewCount))
                    ),
                    criteriaBuilder.sum(
                            criteriaBuilder.sum(
                                    criteriaBuilder.prod(c, criteriaBuilder.toDouble(likeCount)),
                                    criteriaBuilder.prod(d, criteriaBuilder.toDouble(dislikeCount))
                            ),
                            criteriaBuilder.prod(e, criteriaBuilder.toDouble(intervalInDays))
                    )
            );

            // Thêm điều kiện tìm kiếm và sắp xếp
            if (StringUtils.isNotBlank(search)) {
                query.orderBy(
                        criteriaBuilder.desc(priority), // Ưu tiên các kết quả khớp với tìm kiếm
                        criteriaBuilder.desc(customScore) // Sắp xếp tiếp theo dựa trên customScore
                );
            } else {
                query.orderBy(criteriaBuilder.desc(customScore));
            }
            query.groupBy(root.get("id"));

            return criteriaBuilder.and(approvedPredicate, latestVersionPredicate);
        };
    }

    // Hàm xóa dấu
    private String removeAccents(String s) {
        String normalized = java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD);
        return StringUtils.isBlank(s) ? "" : normalized.replaceAll("\\p{M}", "");
    }
}
