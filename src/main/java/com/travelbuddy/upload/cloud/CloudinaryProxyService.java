package com.travelbuddy.upload.cloud;

import com.travelbuddy.upload.cloud.dto.CloudinaryResourceIdDto;
import com.travelbuddy.upload.cloud.dto.FileCloudinaryUploadRqstDto;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import com.travelbuddy.upload.cloud.mapper.CloudinaryProxyMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CloudinaryProxyService implements MediaUploadService<String, FileRspnDto> {

    private final CloudinaryUploadService cloudinaryUploadService;

    private final CloudinaryProxyMapper mapper;

    public CloudinaryProxyService(CloudinaryUploadService cloudinaryUploadService,
                                  CloudinaryProxyMapper mapper) {
        this.cloudinaryUploadService = cloudinaryUploadService;
        this.mapper = mapper;
    }

    @Override
    public FileRspnDto uploadFile(FileUploadRqstDto fileUploadRqstDto) {
        FileCloudinaryUploadRqstDto uploadFile = cloudinaryUploadService.uploadFile(fileUploadRqstDto);

        return mapper.toUploadedFileDto(uploadFile);
    }

    @Override
    public FileRspnDto getFileData(String fileId) {
        CloudinaryResourceIdDto resourceId = mapper.decodeId(fileId);
        FileCloudinaryUploadRqstDto fileData = cloudinaryUploadService.getFileData(resourceId);

        return mapper.toUploadedFileDto(fileData);
    }

    @Override
    public void deleteFile(String fileId) {
        CloudinaryResourceIdDto resourceId = mapper.decodeId(fileId);
        cloudinaryUploadService.deleteFile(resourceId);
    }

    @Override
    public void deleteFiles(List<String> fileIds) {
        List<CloudinaryResourceIdDto> resourceIds = new ArrayList<>();
        for (String fileId : fileIds) {
            resourceIds.add(mapper.decodeId(fileId));
        }
        cloudinaryUploadService.deleteFiles(resourceIds);
    }
}
