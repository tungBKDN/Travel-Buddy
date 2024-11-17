package com.travelbuddy.persistence.domain.dto.sitereview;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SiteReviewCreateRqstDto {
    private Integer siteId;
    private String comment;
    private Integer generalRating;
    private LocalDate arrivalDate;
}
