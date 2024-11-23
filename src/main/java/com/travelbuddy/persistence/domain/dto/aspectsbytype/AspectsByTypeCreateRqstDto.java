package com.travelbuddy.persistence.domain.dto.aspectsbytype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AspectsByTypeCreateRqstDto {
    private Integer typeId;
    private String aspectName;
}
