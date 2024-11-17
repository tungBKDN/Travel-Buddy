package com.travelbuddy.upload.cloud;

import com.travelbuddy.persistence.domain.entity.FileEntity;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;

public interface StorageService extends MediaUploadService<String, FileRspnDto> {
    /**
     * Uploads file to cloud storage, file name is generated automatically,
     * file is stored temporarily and will be deleted after some time
     */
    FileRspnDto uploadFileTemp(FileUploadRqstDto fileUploadRqstDto);

    FileEntity uploadFileEntity(FileUploadRqstDto fileUploadRqstDto);

    /**
     * Makes temporary file permanent
     */
    void makeFilePermanent(String fileId);
}
