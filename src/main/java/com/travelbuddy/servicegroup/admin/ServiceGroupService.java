package com.travelbuddy.servicegroup.admin;
import com.travelbuddy.persistence.domain.dto.servicegroup.ServiceGroupCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.ServiceGroupEntity;

public interface ServiceGroupService {
    Integer createServiceGroup(ServiceGroupCreateRqstDto serviceGroupCreateRqstDto);
    ServiceGroupEntity getServiceGroup(Integer id);
    void updateServiceGroup(Integer serviceGroupId, ServiceGroupCreateRqstDto serviceGroupCreateRqstDto);
}
