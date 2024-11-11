package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MapRepresentationDto {
    private Integer siteId;
    private SiteTypeRspnDto siteType;
    private String name;
    private double lat;
    private double lng;

    public MapRepresentationDto(Integer siteId, SiteTypeEntity siteType, String name, double lat, double lng) {
        this.siteId = siteId;
        this.siteType = new SiteTypeRspnDto();
        this.siteType.mapFromSiteTypeEntity(siteType);
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }
}
