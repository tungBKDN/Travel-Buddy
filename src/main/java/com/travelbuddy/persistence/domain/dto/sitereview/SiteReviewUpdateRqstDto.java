package com.travelbuddy.persistence.domain.dto.sitereview;

import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.List;

@Data
public class SiteReviewUpdateRqstDto {
    @NotNull
    private String comment;
    @NotNull
    @Range(min = 1, max = 5)
    private Integer generalRating;
    @NotNull
    private LocalDate arrivalDate;
    private List<Integer> mediaIds;

    private List<FileRspnDto> newMedias;
}