package com.travelbuddy.service.admin;

import com.travelbuddy.common.exception.errorresponse.DataAlreadyExistsException;
import com.travelbuddy.common.mapper.PageMapper;
import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.service.ServiceCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.ServiceEntity;
import com.travelbuddy.persistence.domain.entity.SiteEntity;
import com.travelbuddy.persistence.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import static com.travelbuddy.common.constants.PaginationLimitConstants.SITE_SERVICE_LIMIT;
import static com.travelbuddy.common.constants.PaginationLimitConstants.SITE_TYPE_LIMIT;

@Service
@RequiredArgsConstructor
public class ServiceServiceImp implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final PageMapper pageMapper;

    @Override
    public void createSiteService(ServiceCreateRqstDto serviceCreateRqstDto) {
        if (serviceRepository.existsByServiceNameIgnoreCase(serviceCreateRqstDto.getServiceName()))
            throw new DataAlreadyExistsException("Service already exists");
        ServiceEntity service = ServiceEntity.builder().serviceName(serviceCreateRqstDto.getServiceName()).build();
    }

    @Override
    public PageDto<ServiceEntity> getAllSiteServices(int page) {
        Pageable pageable = PageRequest.of(page - 1, SITE_SERVICE_LIMIT, Sort.by("serviceName"));
        Page<ServiceEntity> siteServices = serviceRepository.findAll(pageable);
        return pageMapper.toPageDto(siteServices);
    }

    @Override
    public PageDto<ServiceEntity> searchSiteServices(String serviceSearch, int page) {
        Pageable pageable = PageRequest.of(page - 1, SITE_SERVICE_LIMIT, Sort.by("serviceName"));
        Page<ServiceEntity> siteServices = serviceRepository.searchServiceEntitiesByServiceNameContainingIgnoreCase(serviceSearch, pageable);
        return pageMapper.toPageDto(siteServices);
    }
}
