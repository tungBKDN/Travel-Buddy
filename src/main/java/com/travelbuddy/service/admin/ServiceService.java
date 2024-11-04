package com.travelbuddy.service.admin;

import com.travelbuddy.persistence.domain.dto.service.ServiceCreateRqstDto;

public interface ServiceService {
    void createSiteService(ServiceCreateRqstDto serviceCreateRqstDto);
}
