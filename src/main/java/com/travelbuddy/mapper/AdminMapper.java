package com.travelbuddy.mapper;

import com.travelbuddy.persistence.domain.entity.AdminEntity;
import com.travelbuddy.persistence.domain.dto.auth.BasicInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(target = "id", source = "id")
    BasicInfoDto toBasicInfoDto(AdminEntity adminEntity);
}
