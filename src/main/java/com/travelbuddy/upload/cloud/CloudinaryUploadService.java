package com.travelbuddy.upload.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.NotFound;
import com.travelbuddy.upload.cloud.dto.CloudinaryResourceIdDto;
import com.travelbuddy.upload.cloud.dto.FileCloudinaryUploadRqstDto;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import com.travelbuddy.upload.cloud.exception.UploadFileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CloudinaryUploadService implements MediaUploadService<CloudinaryResourceIdDto, FileCloudinaryUploadRqstDto> {
    private final int deleteBatchSize;

    private final Cloudinary cloudinary;

    public CloudinaryUploadService(@Value("${upload.cloudinary.delete-batch-size}") int deleteBatchSize,
                                   @Value("${upload.cloudinary.url}") String cloudinaryUrl) {
        this.deleteBatchSize = deleteBatchSize;
        this.cloudinary = new Cloudinary(cloudinaryUrl);
    }

    @Override
    public FileCloudinaryUploadRqstDto uploadFile(FileUploadRqstDto fileUploadRqstDto) {
        String folder = fileUploadRqstDto.getFolder();

        Map<String, Object> params = new HashMap<>();
        params.put("folder", folder == null ? "" : folder);
        params.put("resource_type", getResourceType(fileUploadRqstDto));

        File tempFile = createTempFile(fileUploadRqstDto.getInputStream(), fileUploadRqstDto.getExtension());
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(tempFile, params);

            tempFile.delete();
            return getUploadedFileDto(uploadResult);
        } catch (IOException e) {
            tempFile.delete();
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileCloudinaryUploadRqstDto getFileData(CloudinaryResourceIdDto fileId) {
        try {
            Map<?, ?> result = cloudinary.api().resource(fileId.getId(),
                    Map.of("resource_type", fileId.getResourceType()));
            return getUploadedFileDto(result);
        } catch (NotFound e) {
            throw new UploadFileNotFoundException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(CloudinaryResourceIdDto fileId) {
        deleteFileFromCloud(fileId);
    }

    @Override
    public void deleteFiles(List<CloudinaryResourceIdDto> fileIds) {
        int fromIndex = 0;
        int toIndex = Math.min(deleteBatchSize, fileIds.size());

        while (fromIndex < fileIds.size()) {
            deleteBatch(fileIds.subList(fromIndex, toIndex));

            fromIndex = toIndex;
            toIndex = Math.min(toIndex + deleteBatchSize, fileIds.size());
        }
    }

    private String getResourceType(FileUploadRqstDto fileUploadRqstDto) {
        String mimeType = fileUploadRqstDto.getMimeType();

        if (mimeType.startsWith("image")) {
            return "image";
        } else if (mimeType.startsWith("video")) {
            return "video";
        } else {
            return "auto";
        }
    }

    private File createTempFile(InputStream inputStream, String fileExtension) {
        try {
            File tempFile = File.createTempFile("TravelBuddy" + File.separator,
                    fileExtension == null ? "" : "." + fileExtension);
            try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tempFile))) {
                inputStream.transferTo(outputStream);
            }

            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileCloudinaryUploadRqstDto getUploadedFileDto(Map<?, ?> uploadResult) {
        FileCloudinaryUploadRqstDto uploadedFileDto = new FileCloudinaryUploadRqstDto();
        uploadedFileDto.setUrl((String) uploadResult.get("secure_url"));

        CloudinaryResourceIdDto resourceId = new CloudinaryResourceIdDto();
        resourceId.setId((String) uploadResult.get("public_id"));
        resourceId.setResourceType((String) uploadResult.get("resource_type"));
        uploadedFileDto.setResourceId(resourceId);

        return uploadedFileDto;
    }

    private void deleteBatch(List<CloudinaryResourceIdDto> fileIdsInBatch) {
        Map<String, List<String>> idsByResourceType = new HashMap<>();
        fileIdsInBatch.forEach(fileId -> {
            idsByResourceType
                    .computeIfAbsent(fileId.getResourceType(), key -> new ArrayList<>())
                    .add(fileId.getId());
        });

        idsByResourceType.forEach((resourceType, ids) -> {
            try {
                cloudinary.api().deleteResources(ids, Map.of("resource_type", resourceType));
            } catch (Exception e) {
                log.error("Failed to delete files in batch", e);
            }
        });
    }

    private void deleteFileFromCloud(CloudinaryResourceIdDto resourceId) {
        try {
            cloudinary.uploader().destroy(resourceId.getId(),
                    Map.of("resource_type", resourceId.getResourceType()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
