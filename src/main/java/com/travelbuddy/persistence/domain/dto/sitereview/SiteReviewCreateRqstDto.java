package com.travelbuddy.persistence.domain.dto.sitereview;

import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.List;

@Data
public class SiteReviewCreateRqstDto {
    @NotNull
    private Integer siteId;
    @NotBlank
    private String comment;
    @NotNull
    @Range(min = 1, max = 5)
    private Integer generalRating;
    @NotNull
    private LocalDate arrivalDate;

    private List<FileRspnDto> medias;
}
