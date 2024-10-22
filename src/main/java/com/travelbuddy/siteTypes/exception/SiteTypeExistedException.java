package com.travelbuddy.siteTypes.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteTypeExistedException extends RuntimeException {
    private Integer exitedSiteTypeId;
}
