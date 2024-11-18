package com.travelbuddy.upload.cloud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileCloudinaryUploadRqstDto {
    private CloudinaryResourceIdDto resourceId;
    private String url;
}
