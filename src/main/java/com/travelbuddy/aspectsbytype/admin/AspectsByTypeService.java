package com.travelbuddy.aspectsbytype.admin;
import com.travelbuddy.persistence.domain.dto.aspectsbytype.AspectsByTypeRepresentationRspndDto;
import java.util.List;

public interface AspectsByTypeService {
    Integer addNewAspect(Integer typeId, String aspectName);
    void deleteAspectById(Integer aspectId);
    void deleteAspectByTypeIdAndAspectName(Integer typeId, String aspectName);
    void updateAspectName(Integer aspectId, String aspectName);
    AspectsByTypeRepresentationRspndDto getAspectById(Integer aspectId);
    List<AspectsByTypeRepresentationRspndDto> getAspectsByTypeId(Integer typeId);
}
