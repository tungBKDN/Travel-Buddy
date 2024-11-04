package com.travelbuddy.service.admin;

import com.travelbuddy.common.exception.errorresponse.DataAlreadyExistsException;
import com.travelbuddy.persistence.domain.dto.service.ServiceCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.ServiceEntity;
import com.travelbuddy.persistence.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceServiceImp implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public void createSiteService(ServiceCreateRqstDto serviceCreateRqstDto) {
//        if (serviceRepository.existedByServiceNameIgnoreCase(serviceCreateRqstDto.getServiceName()))
//            throw new DataAlreadyExistsException("Service already exists");
//        ServiceEntity service = ServiceEntity.builder().serviceName(serviceCreateRqstDto.getServiceName()).build();
    }
}
