package com.travelbuddy.persistence.domain.dto.aspectsbytype;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AspectByTypeCreateRqstDto {
    @NotBlank
    private String aspectName;

    @NotBlank
    private Integer typeId;
}
