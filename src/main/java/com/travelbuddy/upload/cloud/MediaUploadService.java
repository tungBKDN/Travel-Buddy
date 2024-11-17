package com.travelbuddy.upload.cloud;

import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;

import java.util.List;

public interface MediaUploadService<ID, T> {

    /**
     * Uploads file to cloud storage, file name is generated automatically
     */
    T uploadFile(FileUploadRqstDto fileUploadRqstDto);

    /**
     * @throws UploadFileNotFoundException if file not found
     */
    T getFileData(ID fileId);

    void deleteFile(ID fileId);

    void deleteFiles(List<ID> fileIds);
}
