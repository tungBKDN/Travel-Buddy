package com.travelbuddy.common.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelbuddy.common.fileresource.FileResourceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileResourceDtoMapper {
    default FileResourceDto toFileResourceDto(String fileResource) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(fileResource, FileResourceDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default String toString(FileResourceDto fileResourceDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(fileResourceDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
