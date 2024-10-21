package com.travelbuddy.upload.cloud;

import com.travelbuddy.upload.cloud.dto.UploadDto;
import com.travelbuddy.upload.cloud.dto.UploadedFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Primary
@Slf4j
public class CloudinaryStorageService implements StorageService {
    private final long maxAgeMs;

    private final TemporaryFileRepository temporaryFileRepository;

    private final CloudinaryProxyService cloudinaryProxyService;

    public CloudinaryStorageService(@Value("${upload.cloudinary.temporary-file.max-age-ms}") long maxAgeMs,
                                    TemporaryFileRepository temporaryFileRepository,
                                    CloudinaryProxyService cloudinaryProxyService) {
        this.maxAgeMs = maxAgeMs;
        this.temporaryFileRepository = temporaryFileRepository;
        this.cloudinaryProxyService = cloudinaryProxyService;
    }

    @Override
    public UploadedFileDto uploadFile(UploadDto uploadDto) {
        return cloudinaryProxyService.uploadFile(uploadDto);
    }

    @Override
    public UploadedFileDto uploadFileTemp(UploadDto uploadDto) {
        UploadedFileDto uploadedFileDto = cloudinaryProxyService.uploadFile(uploadDto);

        TemporaryFile temporaryFile = new TemporaryFile();
        temporaryFile.setId(uploadedFileDto.getId());
        temporaryFile.setUploadDate(new Date());
        temporaryFileRepository.save(temporaryFile);

        return uploadedFileDto;
    }

    @Override
    public UploadedFileDto getFileData(String fileId) {
        return cloudinaryProxyService.getFileData(fileId);
    }

    @Override
    public void deleteFile(String fileId) {
        cloudinaryProxyService.deleteFile(fileId);
        temporaryFileRepository.deleteById(fileId);
    }

    @Override
    public void deleteFiles(List<String> fileIds) {
        cloudinaryProxyService.deleteFiles(fileIds);
        temporaryFileRepository.deleteAllById(fileIds);
    }

    @Override
    public void makeFilePermanent(String fileId) {
        temporaryFileRepository.deleteById(fileId);
    }

    @Scheduled(fixedRateString = "${upload.cloudinary.temporary-file.delete-interval-ms}",
            initialDelayString = "${upload.cloudinary.temporary-file.initial-delay-ms}")
    private void deleteTemporaryFiles() {
        log.info("Deleting expired temporary files");

        Date maxAgeDate = new Date(System.currentTimeMillis() - maxAgeMs);
        List<TemporaryFile> temporaryFiles = temporaryFileRepository.findAllByUploadDateBefore(maxAgeDate);
        cloudinaryProxyService.deleteFiles(temporaryFiles.stream().map(TemporaryFile::getId).toList());
        temporaryFileRepository.deleteAll(temporaryFiles);

        log.info("Deleted {} expired temporary files", temporaryFiles.size());
    }
}
