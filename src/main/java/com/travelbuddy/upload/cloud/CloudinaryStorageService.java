package com.travelbuddy.upload.cloud;

import com.travelbuddy.persistence.domain.entity.FileEntity;
import com.travelbuddy.persistence.repository.FileRepository;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Primary
@Slf4j
@Transactional
public class CloudinaryStorageService implements StorageService {
    private final long maxAgeMs;

    private final FileRepository fileRepository;

    private final CloudinaryProxyService cloudinaryProxyService;

    public CloudinaryStorageService(@Value("${upload.cloudinary.temporary-file.max-age-ms}") long maxAgeMs,
                                    FileRepository fileRepository,
                                    CloudinaryProxyService cloudinaryProxyService) {
        this.maxAgeMs = maxAgeMs;
        this.fileRepository = fileRepository;
        this.cloudinaryProxyService = cloudinaryProxyService;
    }

    @Override
    public FileRspnDto uploadFile(FileUploadRqstDto fileUploadRqstDto) {
        return cloudinaryProxyService.uploadFile(fileUploadRqstDto);
    }

    @Override
    public FileRspnDto uploadFileTemp(FileUploadRqstDto fileUploadRqstDto) {

//        FileEntity fileEntity = new FileEntity();
//        fileEntity.setId(fileRspnDto.getId());
//        fileEntity.setUrl(fileRspnDto.getUrl());
//        fileRepository.saveAndFlush(fileEntity);

        return cloudinaryProxyService.uploadFile(fileUploadRqstDto);
    }

    @Override
    public FileEntity uploadFileEntity(FileUploadRqstDto fileUploadRqstDto) {
        FileRspnDto fileRspnDto = cloudinaryProxyService.uploadFile(fileUploadRqstDto);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(fileRspnDto.getId());
        fileEntity.setUrl(fileRspnDto.getUrl());
        fileRepository.saveAndFlush(fileEntity);

        return fileEntity;
    }

    @Override
    public FileRspnDto getFileData(String fileId) {
        return cloudinaryProxyService.getFileData(fileId);
    }

    @Override
    public void deleteFile(String fileId) {
        cloudinaryProxyService.deleteFile(fileId);
    }

    @Override
    public void deleteFiles(List<String> fileIds) {
        cloudinaryProxyService.deleteFiles(fileIds);
    }

    @Override
    public void makeFilePermanent(String fileId) {
        fileRepository.deleteById(fileId);
    }

    @Scheduled(fixedRateString = "${upload.cloudinary.temporary-file.delete-interval-ms}",
            initialDelayString = "${upload.cloudinary.temporary-file.initial-delay-ms}")
    private void deleteTemporaryFiles() {
        log.info("Deleting expired temporary files");

        LocalDateTime maxAgeLocalDateTime = LocalDateTime.now().minusNanos(maxAgeMs * 1_000_000);
        List<FileEntity> fileEntities = fileRepository.findAllByCreatedAtBefore(maxAgeLocalDateTime);
        cloudinaryProxyService.deleteFiles(fileEntities.stream().map(FileEntity::getId).toList());
        fileRepository.deleteAll(fileEntities);

        log.info("Deleted {} expired temporary files", fileEntities.size());
    }
}
