package com.travelbuddy.upload.cloud;

import com.travelbuddy.upload.cloud.dto.CloudinaryResourceId;
import com.travelbuddy.upload.cloud.dto.CloudinaryUploadedFileDto;
import com.travelbuddy.upload.cloud.dto.UploadDto;
import com.travelbuddy.upload.cloud.dto.UploadedFileDto;
import com.travelbuddy.upload.cloud.mapper.CloudinaryProxyMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CloudinaryProxyService implements MediaUploadService<String, UploadedFileDto> {

    private final CloudinaryUploadService cloudinaryUploadService;

    private final CloudinaryProxyMapper mapper;

    public CloudinaryProxyService(CloudinaryUploadService cloudinaryUploadService,
                                  CloudinaryProxyMapper mapper) {
        this.cloudinaryUploadService = cloudinaryUploadService;
        this.mapper = mapper;
    }

    @Override
    public UploadedFileDto uploadFile(UploadDto uploadDto) {
        CloudinaryUploadedFileDto uploadFile = cloudinaryUploadService.uploadFile(uploadDto);

        return mapper.toUploadedFileDto(uploadFile);
    }

    @Override
    public UploadedFileDto getFileData(String fileId) {
        CloudinaryResourceId resourceId = mapper.decodeId(fileId);
        CloudinaryUploadedFileDto fileData = cloudinaryUploadService.getFileData(resourceId);

        return mapper.toUploadedFileDto(fileData);
    }

    @Override
    public void deleteFile(String fileId) {
        CloudinaryResourceId resourceId = mapper.decodeId(fileId);
        cloudinaryUploadService.deleteFile(resourceId);
    }

    @Override
    public void deleteFiles(List<String> fileIds) {
        List<CloudinaryResourceId> resourceIds = new ArrayList<>();
        for (String fileId : fileIds) {
            resourceIds.add(mapper.decodeId(fileId));
        }
        cloudinaryUploadService.deleteFiles(resourceIds);
    }
}
