package com.travelbuddy.sitereviews;

import com.travelbuddy.common.utils.RequestUtils;
import com.travelbuddy.persistence.domain.entity.SiteReviewEntity;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class SiteReviewSpecifications {
    private final RequestUtils requestUtils;

    public Specification<SiteReviewEntity> customScoreBySiteId(Long siteId) {
        // Đặt các hệ số cho thuật toán
        double a = 1.0;
        double b = 0.5;
        double c = -0.1;
        double d = 0.2;
        double e = -0.3;

        return (root, query, criteriaBuilder) -> {
            // Điều kiện lọc theo siteId
            Predicate postPredicate = criteriaBuilder.equal(root.get("siteEntity").get("id"), siteId);

            int currentUserId = requestUtils.getUserIdCurrentRequest();
            Predicate userPredicate = criteriaBuilder.equal(root.get("userId"), currentUserId);
            Expression<Integer> caseExpression = criteriaBuilder.<Integer>selectCase()
                    .when(userPredicate, 1)
                    .otherwise(0);

            // Tham chiếu đến các trường dữ liệu cần thiết
            Expression<Double> generalRating = criteriaBuilder.toDouble(root.get("generalRating"));
            Expression<Double> fileCount = criteriaBuilder.toDouble(criteriaBuilder.count(root.join("reviewMedias", JoinType.LEFT)));

            Expression<Double> likeCount = criteriaBuilder.toDouble(
                    criteriaBuilder.count(root.join("reviewReactions", JoinType.LEFT).get("reactionType").in("LIKE"))
            );

            Expression<Double> dislikeCount = criteriaBuilder.toDouble(
                    criteriaBuilder.count(root.join("reviewReactions", JoinType.LEFT).get("reactionType").in("DISLIKE"))
            );


            Expression<Number> intervalInDays = criteriaBuilder.function(
                    "DATE_PART", // PostgreSQL hàm để lấy ngày từ INTERVAL
                    Number.class,
                    criteriaBuilder.literal("day"),
                    criteriaBuilder.function("AGE", Date.class, criteriaBuilder.currentTimestamp(), root.get("createdAt"))
            );

            Expression<Double> daysSinceCreation = criteriaBuilder.toDouble(intervalInDays);


            Expression<Double> customScore = criteriaBuilder.sum(
                    criteriaBuilder.sum(
                            criteriaBuilder.sum(
                                    criteriaBuilder.prod(a, generalRating),
                                    criteriaBuilder.prod(b, fileCount)
                            ),
                            criteriaBuilder.prod(c, daysSinceCreation)
                    ),
                    criteriaBuilder.sum(
                            criteriaBuilder.prod(d, likeCount),
                            criteriaBuilder.prod(e, dislikeCount)
                    )
            );

            // Thêm customScore vào phần ORDER BY
            assert query != null;
            query.orderBy(criteriaBuilder.desc(caseExpression), criteriaBuilder.desc(customScore));
            query.groupBy(root.get("id")); // Nhóm theo id của review nếu cần

            return postPredicate;
        };
    }
}
