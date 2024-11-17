package com.travelbuddy.mapper;

import com.travelbuddy.persistence.domain.entity.FileEntity;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileRspnDto toFileRspnDto(FileEntity fileEntity);
}
