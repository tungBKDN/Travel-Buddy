package com.travelbuddy.fee;

import com.travelbuddy.persistence.domain.dto.fee.CreateFeeRqstDto;
import com.travelbuddy.persistence.domain.dto.fee.FeeRspndDto;
import com.travelbuddy.persistence.domain.entity.AspectsByTypeEntity;
import com.travelbuddy.persistence.domain.entity.FeeEntity;
import com.travelbuddy.persistence.repository.AspectsByTypeRepository;
import com.travelbuddy.persistence.repository.FeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {
    private final FeeRepository feeRepository;
    private final AspectsByTypeRepository aspectsByTypeRepository;

    @Override
    public void addFee(List<CreateFeeRqstDto> createFeeRqstDtos, Integer siteVersionId) {
        createFeeRqstDtos = Optional.ofNullable(createFeeRqstDtos).orElse(Collections.emptyList());
        List<FeeEntity> feeEntities = new ArrayList<>();
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
        List<FeeEntity> fees = feeRepository.findAllBySiteVersionId(siteVersionId).orElse(new ArrayList<>());
        List<FeeRspndDto> feeRspndDtos = new ArrayList<>();
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
