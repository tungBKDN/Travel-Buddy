package com.travelbuddy.mapper;

import com.travelbuddy.persistence.domain.entity.AdminEntity;
import com.travelbuddy.persistence.domain.dto.auth.BasicInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "avatar", source = "avatar.url")
    BasicInfoDto toBasicInfoDto(AdminEntity adminEntity);
}
