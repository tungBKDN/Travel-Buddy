package com.travelbuddy.mapper;

import com.travelbuddy.common.mapper.JacksonMapper;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static com.travelbuddy.common.constants.DualStateEnum.AMENITY;
import static com.travelbuddy.common.constants.DualStateEnum.ATTRACTION;

@Mapper(componentModel = "spring", uses = {JacksonMapper.class})
public interface SiteTypeMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "typeName")
    @Mapping(target = "attraction", expression = "java(isAttraction(siteTypeEntity))")
    @Mapping(target = "amenity", expression = "java(isAmenity(siteTypeEntity))")
    SiteTypeRspnDto siteTypeEntityToSiteTypeRspnDto(SiteTypeEntity siteTypeEntity);

    default boolean isAttraction(SiteTypeEntity siteTypeEntity) {
        return siteTypeEntity != null && siteTypeEntity.getDualState() != AMENITY;
    }

    default boolean isAmenity(SiteTypeEntity siteTypeEntity) {
        return siteTypeEntity != null && siteTypeEntity.getDualState() != ATTRACTION;
    }
}
