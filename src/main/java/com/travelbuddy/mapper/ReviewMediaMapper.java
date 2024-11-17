package com.travelbuddy.mapper;

import com.travelbuddy.persistence.domain.dto.sitereview.MediaRspnDto;
import com.travelbuddy.persistence.domain.entity.ReviewMediaEntity;
import com.travelbuddy.persistence.domain.entity.SiteMediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMediaMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "url", source = "media.url")
    @Mapping(target = "mediaType", source = "mediaType")
    @Mapping(target = "createdAt", source = "media.createdAt")
    MediaRspnDto reviewMediaToMediaRspnDto(ReviewMediaEntity reviewMediaEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "url", source = "media.url")
    @Mapping(target = "mediaType", source = "mediaType")
    @Mapping(target = "createdAt", source = "media.createdAt")
    MediaRspnDto siteMediaToMediaRspnDto(SiteMediaEntity siteMediaEntity);
}
