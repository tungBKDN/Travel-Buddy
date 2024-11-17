package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.persistence.domain.dto.sitereview.MediaRspnDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapRepresentationDto {
    private Integer siteId;
    private SiteTypeRspnDto siteType;
    private String name;
    private double lat;
    private double lng;
    private List<MediaRspnDto> medias;

    private Double averageRating;
    private Integer totalRating;
}
