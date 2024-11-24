package com.travelbuddy.fee;

import com.travelbuddy.persistence.domain.dto.fee.CreateFeeRqstDto;
import com.travelbuddy.persistence.domain.dto.fee.FeeRspndDto;
import com.travelbuddy.persistence.domain.entity.AspectsByTypeEntity;
import com.travelbuddy.persistence.domain.entity.FeeEntity;
import com.travelbuddy.persistence.repository.AspectsByTypeRepository;
import com.travelbuddy.persistence.repository.FeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FeeServiceImp implements FeeService {
    private final FeeRepository feeRepository;
    private final AspectsByTypeRepository aspectsByTypeRepository;

    @Override
    public void addFee(List<CreateFeeRqstDto> createFeeRqstDtos, Integer siteVersionId) {
        List<FeeEntity> feeEntities = new ArrayList<FeeEntity>();
        for (CreateFeeRqstDto createFeeRqstDto : createFeeRqstDtos) {
            FeeEntity feeEntity = new FeeEntity();
            feeEntity.setSiteVersionId(siteVersionId);
            feeEntity.setAspectId(createFeeRqstDto.getAspectId());
            feeEntity.setFeeLow(createFeeRqstDto.getFeeLow());
            feeEntity.setFeeHigh(createFeeRqstDto.getFeeHigh());
            feeEntities.add(feeEntity);
        }
        feeRepository.saveAll(feeEntities);
    }

    @Override
    public List<FeeRspndDto> getFeeBySiteVersionId(Integer siteVersionId) {
        List<FeeEntity> fees = feeRepository.findAllBySiteVersionId(siteVersionId).orElse(new ArrayList<FeeEntity>());
        List<FeeRspndDto> feeRspndDtos = new ArrayList<FeeRspndDto>();
        for (FeeEntity fee : fees) {
            AspectsByTypeEntity aspect = aspectsByTypeRepository.findById(fee.getAspectId()).orElse(null);
            FeeRspndDto feeRspndDto = new FeeRspndDto();
            feeRspndDto.setId(fee.getId());
            feeRspndDto.setAspect(aspect);
            feeRspndDto.setFeeLow(fee.getFeeLow());
            feeRspndDto.setFeeHigh(fee.getFeeHigh());
            feeRspndDtos.add(feeRspndDto);
        }
        return feeRspndDtos;
    }
}
