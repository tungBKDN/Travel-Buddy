package com.travelbuddy.common.fileresource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalFileResourceDto extends FileResourceDto {
    private String url;
}
