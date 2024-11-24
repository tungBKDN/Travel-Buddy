package com.travelbuddy.fee;

import com.travelbuddy.persistence.domain.dto.fee.CreateFeeRqstDto;
import com.travelbuddy.persistence.domain.dto.fee.FeeRspndDto;

import java.util.List;

public interface FeeService {
    void addFee(List<CreateFeeRqstDto> createFeeRqstDtos, Integer siteVersionId);
    List<FeeRspndDto> getFeeBySiteVersionId(Integer siteVersionId);
}
