package com.travelbuddy.mapper;

import com.travelbuddy.persistence.domain.dto.auth.BasicInfoDto;
import com.travelbuddy.persistence.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    BasicInfoDto toBasicInfoDto(UserEntity userEntity);
}
