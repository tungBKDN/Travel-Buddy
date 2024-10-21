package com.travelbuddy.common.fileresource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CloudinaryFileResourceDto.class, name = ResourceStorages.CLOUDINARY),
        @JsonSubTypes.Type(value = ExternalFileResourceDto.class, name = ResourceStorages.EXTERNAL)
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class FileResourceDto {
    private MediaType mediaType;
}
