package com.travelbuddy.mapper;

import com.travelbuddy.persistence.domain.dto.account.user.UserDetailRspnDto;
import com.travelbuddy.persistence.domain.dto.account.user.UserPublicInfoRspnDto;
import com.travelbuddy.persistence.domain.dto.account.user.UserSearchRspnDto;
import com.travelbuddy.persistence.domain.dto.auth.BasicInfoDto;
import com.travelbuddy.persistence.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "avatar", source = "avatar.url")
    BasicInfoDto toBasicInfoDto(UserEntity userEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "socialUrl", source = "socialUrl")
    @Mapping(target = "avatar", source = "avatar.url")
    @Mapping(target = "score", source = "score")
    @Mapping(target = "createdAt", expression = "java(userEntity.getCreatedAt().toString())")
    UserDetailRspnDto toUserDetailRspnDto(UserEntity userEntity);

    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "avatar", source = "avatar.url")
    @Mapping(target = "socialUrl", source = "socialUrl")
    @Mapping(target = "score", source = "score")
    @Mapping(target = "createdAt", expression = "java(user.getCreatedAt().toString())")
    UserPublicInfoRspnDto toUserPublicInfoRspnDto(UserEntity user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "avatar", source = "avatar.url")
    UserSearchRspnDto toUserSearchRspnDto(UserEntity userEntity);
}
