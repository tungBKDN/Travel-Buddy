package com.travelbuddy.aspectsbytype.admin;
import com.travelbuddy.common.exception.errorresponse.DataAlreadyExistsException;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.persistence.domain.entity.AspectsByTypeEntity;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import com.travelbuddy.persistence.repository.AspectsByTypeRepository;
import com.travelbuddy.persistence.repository.SiteTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.travelbuddy.persistence.domain.dto.aspectsbytype.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AspectsByTypeServiceImp implements AspectsByTypeService {
    private final AspectsByTypeRepository aspectsByTypeRepository;
    private final SiteTypeRepository siteTypeRepository;

    @Override
    public Integer addNewAspect(Integer typeId, String aspectName) {
        if (aspectsByTypeRepository.existsByTypeIdAndAspectNameIgnoreCase(typeId, aspectName)) {
            throw new DataAlreadyExistsException("Aspect already exists for this type");
        }
        return aspectsByTypeRepository.save(AspectsByTypeEntity.builder().typeId(typeId).aspectName(aspectName).build()).getId();
    }

    public void deleteAspectById(Integer aspectId) {
        if (!aspectsByTypeRepository.existsById(aspectId)) {
            throw new NotFoundException("Aspect with id " + aspectId + " not found");
        }
        aspectsByTypeRepository.deleteById(aspectId);
    }

    public void deleteAspectByTypeIdAndAspectName(Integer typeId, String aspectName) {
        if (!aspectsByTypeRepository.existsByTypeIdAndAspectNameIgnoreCase(typeId, aspectName)) {
            throw new NotFoundException("Aspect with type id " + typeId + " and aspect name " + aspectName + " not found");
        }
        aspectsByTypeRepository.deleteByTypeIdAndAspectNameIgnoreCase(typeId, aspectName);
    }

    public void updateAspectName(Integer aspectId, String aspectName) {
        if (!aspectsByTypeRepository.existsById(aspectId)) {
            throw new NotFoundException("Aspect with id " + aspectId + " not found");
        }
        AspectsByTypeEntity aspect = aspectsByTypeRepository.findById(aspectId).get();
        aspect.setAspectName(aspectName);
        aspectsByTypeRepository.save(aspect);
    }

    public List<ViewAspectByTypeRspndDto> getAspectsByTypeId(Integer typeId) {
        List<AspectsByTypeEntity> aspects = aspectsByTypeRepository.findAllByTypeId(typeId).orElseThrow(() -> new NotFoundException("No aspects found for type id " + typeId));
        List<ViewAspectByTypeRspndDto> viewAspects = new ArrayList<ViewAspectByTypeRspndDto>();
        for (AspectsByTypeEntity aspect : aspects) {
            SiteTypeEntity siteType = siteTypeRepository.findById(typeId).get();
            ViewAspectByTypeRspndDto viewAspectByTypeRspndDto = new ViewAspectByTypeRspndDto();
            viewAspectByTypeRspndDto.setAspectId(aspect.getId());
            viewAspectByTypeRspndDto.setSiteType(siteType);
            viewAspectByTypeRspndDto.setAspectName(aspect.getAspectName());
            viewAspects.add(viewAspectByTypeRspndDto);
        }
        return null;
    }
}