package com.travelbuddy.siteTypes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSiteTypeDto {
    @NotBlank
    private String siteTypeName;

    @NotBlank
    private String mode; // This resembles ENUM(ATTRACTION, UTILITY, DUAL)
}
