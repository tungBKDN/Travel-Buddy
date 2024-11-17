package com.travelbuddy.servicegroup.admin;
import com.travelbuddy.persistence.domain.dto.servicegroup.ServiceGroupCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.ServiceGroupEntity;

public interface ServiceGroupService {
    Integer createServiceGroup(ServiceGroupCreateRqstDto serviceGroupCreateRqstDto);
    ServiceGroupEntity getServiceGroup(Integer id);
    void updateServiceGroup(Integer serviceGroupId, ServiceGroupCreateRqstDto serviceGroupCreateRqstDto);

    void associateService(Integer serviceGroupId, Integer serviceId);
    void detachService(Integer serviceGroupId, Integer serviceId);
    void detachService(Integer id);

    void associateType(Integer serviceGroupId, Integer typeId);
    void detachType(Integer serviceGroupId, Integer typeId);
    void detachType(Integer id);
}
