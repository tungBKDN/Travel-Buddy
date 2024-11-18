package com.travelbuddy.persistence.domain.dto.sitereview;

import com.travelbuddy.persistence.domain.dto.auth.BasicInfoDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class SiteReviewRspnDto {
    private Integer id;
    private Integer generalRating;
    private String comment;
    private String date;
    private BasicInfoDto user;
    private List<MediaRspnDto> medias;
    private Boolean isEdited;
    private Integer likeCount;
    private Integer dislikeCount;
    private String userReaction;
    private String arrivalDate;
}
