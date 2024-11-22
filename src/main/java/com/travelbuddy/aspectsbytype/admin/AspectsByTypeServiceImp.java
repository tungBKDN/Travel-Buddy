package com.travelbuddy.aspectsbytype.admin;
import com.travelbuddy.common.exception.errorresponse.DataAlreadyExistsException;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.persistence.domain.entity.AspectsByTypeEntity;
import com.travelbuddy.persistence.repository.AspectsByTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AspectsByTypeServiceImp implements AspectsByTypeService {
    private final AspectsByTypeRepository aspectsByTypeRepository;

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

}