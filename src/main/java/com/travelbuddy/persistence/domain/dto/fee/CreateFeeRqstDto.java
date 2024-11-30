package com.travelbuddy.persistence.domain.dto.fee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFeeRqstDto {
    private Integer aspectId;
    private Integer feeLow;
    private Integer feeHigh;
}
