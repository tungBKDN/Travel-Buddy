package com.travelbuddy.persistence.domain.dto.sitereview;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SiteReviewUpdateRqstDto {
    private String comment;
    private Integer generalRating;
    private LocalDate arrivalDate;
    private List<Integer> mediaIds;
}