package com.travelbuddy.persistence.domain.dto.sitetype;

import lombok.Data;

@Data
public class SiteTypeRspnDto {
    private int id;
    private String name;
    private boolean isAttraction;
    private boolean isAmenity;
}
