package com.travelbuddy.upload.cloud.dto;

import lombok.*;

import java.io.InputStream;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRqstDto {
    private InputStream inputStream;

    private String folder;

    private String mimeType;

    private String extension;
}
