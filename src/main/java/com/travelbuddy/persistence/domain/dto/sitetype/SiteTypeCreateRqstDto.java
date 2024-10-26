package com.travelbuddy.persistence.domain.dto.sitetype;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteTypeCreateRqstDto {
    @NotBlank
    private String siteTypeName;

    @NotBlank
    private String mode; // This resembles ENUM(ATTRACTION, AMENITY, DUAL)
}
