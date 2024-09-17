package com.travelbuddy.upload.cloud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloudinaryUploadedFileDto {
    private CloudinaryResourceId resourceId;
    private String url;
}
