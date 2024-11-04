package com.travelbuddy.service.admin;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.service.ServiceCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.ServiceEntity;

public interface ServiceService {
    void createSiteService(ServiceCreateRqstDto serviceCreateRqstDto);
    PageDto<ServiceEntity> getAllSiteServices(int page);
    PageDto<ServiceEntity> searchSiteServices(String serviceSearch, int page);
}
