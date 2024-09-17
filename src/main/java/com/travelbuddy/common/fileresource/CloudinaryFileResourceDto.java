package com.travelbuddy.common.fileresource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloudinaryFileResourceDto extends FileResourceDto {
    private String publicId;
    private String url;
}
