package com.travelbuddy.mapper;

import com.travelbuddy.common.constants.ReactionTypeEnum;
import com.travelbuddy.common.mapper.JacksonMapper;
import com.travelbuddy.persistence.domain.dto.auth.BasicInfoDto;
import com.travelbuddy.persistence.domain.dto.sitereview.MediaRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewDetailRspnDto;
import com.travelbuddy.persistence.domain.dto.sitereview.SiteReviewRspnDto;
import com.travelbuddy.persistence.domain.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {JacksonMapper.class})
public interface SiteReviewMapper {
    @Mapping(target = "id", source = "siteReviewEntity.id")
    @Mapping(target = "generalRating", source = "siteReviewEntity.generalRating")
    @Mapping(target = "comment", source = "siteReviewEntity.comment")
    @Mapping(target = "date", expression = "java(getSiteReviewDate(siteReviewEntity))")
    @Mapping(target = "medias", expression = "java(toReviewMediaRspnDto(siteReviewEntity.getReviewMedias()))")
    @Mapping(target = "user", expression = "java(toBasicInfoDto(siteReviewEntity.getUserEntity()))")
    @Mapping(target = "isEdited", expression = "java(siteReviewEntity.getUpdatedAt() != null)")
    @Mapping(target = "likeCount", source = "siteReviewEntity.reviewReactions", qualifiedByName = "likeCount")
    @Mapping(target = "dislikeCount", source = "siteReviewEntity.reviewReactions", qualifiedByName = "dislikeCount")
    @Mapping(target = "userReaction", expression = "java(userReaction(siteReviewEntity.getReviewReactions(), userId))")
    @Mapping(target = "arrivalDate", source = "siteReviewEntity.arrivalDate")
    SiteReviewRspnDto siteReviewEntityToSiteReviewRspnDto(SiteReviewEntity siteReviewEntity, int userId);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "generalRating", source = "generalRating")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "arrivalDate", source = "arrivalDate")
    @Mapping(target = "medias", expression = "java(toReviewMediaRspnDto(siteReviewEntity.getReviewMedias()))")
    SiteReviewDetailRspnDto siteReviewEntityToSiteReviewDetailRspnDto(SiteReviewEntity siteReviewEntity);

    default String getSiteReviewDate(SiteReviewEntity siteReviewEntity) {
        return siteReviewEntity.getUpdatedAt() != null ? siteReviewEntity.getUpdatedAt().toString() : siteReviewEntity.getCreatedAt().toString();
    }

    default BasicInfoDto toBasicInfoDto(UserEntity userEntity) {
        return BasicInfoDto.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .nickname(userEntity.getNickname())
                .avatar(userEntity.getAvatar() != null ? userEntity.getAvatar().getUrl() : null)
                .build();
    }

    default List<MediaRspnDto> toReviewMediaRspnDto(List<ReviewMediaEntity> reviewMediaEntities) {
        return reviewMediaEntities.stream()
                .map(reviewMediaEntity -> MediaRspnDto.builder()
                        .id(reviewMediaEntity.getId())
                        .url(reviewMediaEntity.getMedia().getUrl())
                        .mediaType(reviewMediaEntity.getMediaType())
                        .createdAt(reviewMediaEntity.getMedia().getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Named("likeCount")
    default Integer likeCount(List<ReviewReactionEntity> reviewReactionEntities) {
        return (int) reviewReactionEntities.stream()
                .filter(reviewReactionEntity -> ReactionTypeEnum.LIKE.name().equals(reviewReactionEntity.getReactionType()))
                .count();
    }

    @Named("dislikeCount")
    default Integer dislikeCount(List<ReviewReactionEntity> reviewReactionEntities) {
        return (int) reviewReactionEntities.stream()
                .filter(reviewReactionEntity -> ReactionTypeEnum.DISLIKE.name().equals(reviewReactionEntity.getReactionType()))
                .count();
    }

    default String userReaction(List<ReviewReactionEntity> reviewReactionEntities, int userId) {
        return reviewReactionEntities.stream()
                .filter(reviewReactionEntity -> userId == reviewReactionEntity.getUserId())
                .findFirst()
                .map(ReviewReactionEntity::getReactionType)
                .orElse(null);
    }
}
