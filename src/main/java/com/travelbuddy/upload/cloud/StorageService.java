package com.travelbuddy.upload.cloud;

import com.travelbuddy.upload.cloud.dto.UploadDto;
import com.travelbuddy.upload.cloud.dto.UploadedFileDto;

public interface StorageService extends MediaUploadService<String, UploadedFileDto> {
    /**
     * Uploads file to cloud storage, file name is generated automatically,
     * file is stored temporarily and will be deleted after some time
     */
    UploadedFileDto uploadFileTemp(UploadDto uploadDto);

    /**
     * Makes temporary file permanent
     */
    void makeFilePermanent(String fileId);
}
