package com.travelbuddy.aspectsbytype.admin;
import com.travelbuddy.persistence.domain.dto.aspectsbytype.*;

import java.util.List;

public interface AspectsByTypeService {
    Integer addNewAspect(Integer typeId, String aspectName);
    void deleteAspectById(Integer aspectId);
    void deleteAspectByTypeIdAndAspectName(Integer typeId, String aspectName);
    void updateAspectName(Integer aspectId, String aspectName);
    void getAspectById(Integer aspectId);
    List<ViewAspectByTypeRspndDto> getAspectsByTypeId(Integer typeId);
}
