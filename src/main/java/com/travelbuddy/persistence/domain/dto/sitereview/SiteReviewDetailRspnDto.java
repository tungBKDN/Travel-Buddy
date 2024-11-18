package com.travelbuddy.persistence.domain.dto.sitereview;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class SiteReviewDetailRspnDto {
    private Integer id;
    private Integer generalRating;
    private String comment;
    private LocalDate arrivalDate;
    private List<MediaRspnDto> medias;
}
