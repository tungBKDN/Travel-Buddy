package com.travelbuddy.servicegroup.admin;

import com.travelbuddy.common.exception.errorresponse.DataAlreadyExistsException;
import com.travelbuddy.common.exception.errorresponse.NotFoundException;
import com.travelbuddy.persistence.domain.dto.servicegroup.ServiceGroupCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.ServiceGroupEntity;
import com.travelbuddy.persistence.repository.ServiceGroupRepository;
import com.travelbuddy.persistence.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceGroupServiceImp implements ServiceGroupService {
    private final ServiceGroupRepository serviceGroupRepository;

    @Override
    public void updateServiceGroup(Integer serviceGroupId, ServiceGroupCreateRqstDto serviceGroupCreateRqstDto) {
        ServiceGroupEntity serviceGroupEntity = serviceGroupRepository.findById(serviceGroupId).orElseThrow(() -> new NotFoundException("Service group with id " + serviceGroupId + " not found"));
        if (serviceGroupRepository.existsByServiceGroupNameIgnoreCase(serviceGroupCreateRqstDto.getGroupName())) {
            throw new DataAlreadyExistsException("Service group with name " + serviceGroupCreateRqstDto.getGroupName() + " already exists");
        }
        serviceGroupEntity.setServiceGroupName(serviceGroupCreateRqstDto.getGroupName());
        serviceGroupRepository.save(serviceGroupEntity);
    }

    @Override
    public Integer createServiceGroup(ServiceGroupCreateRqstDto serviceGroupCreateRqstDto) {
        if (serviceGroupRepository.existsByServiceGroupNameIgnoreCase(serviceGroupCreateRqstDto.getGroupName())) {
            throw new DataAlreadyExistsException("Service group with name " + serviceGroupCreateRqstDto.getGroupName() + " already exists");
        }
        ServiceGroupEntity serviceGroupEntity = ServiceGroupEntity.builder()
                .serviceGroupName(serviceGroupCreateRqstDto.getGroupName())
                .build();
        return serviceGroupRepository.save(serviceGroupEntity).getId();
    }

    @Override
    public ServiceGroupEntity getServiceGroup(Integer id) {
        return serviceGroupRepository.findById(id).orElseThrow(() -> new NotFoundException("Service group with id " + id + " not found"));
    }
}
