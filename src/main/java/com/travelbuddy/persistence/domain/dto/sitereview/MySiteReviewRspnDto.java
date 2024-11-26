package com.travelbuddy.persistence.domain.dto.sitereview;

import com.travelbuddy.persistence.domain.dto.site.SiteBasicInfoRspnDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MySiteReviewRspnDto {
    private Integer id;
    private Integer siteId;
    private Integer generalRating;
    private String comment;
    private String date;
    private List<MediaRspnDto> medias;
    private Boolean isEdited;
    private Integer likeCount;
    private Integer dislikeCount;
    private String userReaction;
    private String arrivalDate;
    private SiteBasicInfoRspnDto site;
}
