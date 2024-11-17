package com.travelbuddy.upload.cloud.mapper;

import com.travelbuddy.upload.cloud.dto.CloudinaryResourceIdDto;
import com.travelbuddy.upload.cloud.dto.FileCloudinaryUploadRqstDto;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface CloudinaryProxyMapper {

    @Mapping(target = "id", source = "resourceId")
    @Mapping(target = "url", source = "url")
//    @Mapping(target = "createdAt", ignore = true)
    FileRspnDto toUploadedFileDto(FileCloudinaryUploadRqstDto fileCloudinaryUploadRqstDto);

    default String encodeId(CloudinaryResourceIdDto cloudinaryResourceIdDto) {
        String id = String.format("%s&%s",
                cloudinaryResourceIdDto.getId(),
                cloudinaryResourceIdDto.getResourceType());

        return Base64.getEncoder().encodeToString(id.getBytes());
    }

    default CloudinaryResourceIdDto decodeId(String id) {
        String decodedId = new String(Base64.getDecoder().decode(id));
        String[] parts = decodedId.split("&");

        CloudinaryResourceIdDto cloudinaryResourceIdDto = new CloudinaryResourceIdDto();
        cloudinaryResourceIdDto.setId(parts[0]);
        cloudinaryResourceIdDto.setResourceType(parts[1]);

        return cloudinaryResourceIdDto;
    }
}
