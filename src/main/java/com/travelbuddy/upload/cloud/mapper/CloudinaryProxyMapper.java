package com.travelbuddy.upload.cloud.mapper;

import com.travelbuddy.upload.cloud.dto.CloudinaryResourceId;
import com.travelbuddy.upload.cloud.dto.CloudinaryUploadedFileDto;
import com.travelbuddy.upload.cloud.dto.UploadedFileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface CloudinaryProxyMapper {

    @Mapping(target = "id", source = "resourceId")
    UploadedFileDto toUploadedFileDto(CloudinaryUploadedFileDto cloudinaryUploadedFileDto);

    default String encodeId(CloudinaryResourceId cloudinaryResourceId) {
        String id = String.format("%s&%s",
                cloudinaryResourceId.getId(),
                cloudinaryResourceId.getResourceType());

        return Base64.getEncoder().encodeToString(id.getBytes());
    }

    default CloudinaryResourceId decodeId(String id) {
        String decodedId = new String(Base64.getDecoder().decode(id));
        String[] parts = decodedId.split("&");

        CloudinaryResourceId cloudinaryResourceId = new CloudinaryResourceId();
        cloudinaryResourceId.setId(parts[0]);
        cloudinaryResourceId.setResourceType(parts[1]);

        return cloudinaryResourceId;
    }
}
