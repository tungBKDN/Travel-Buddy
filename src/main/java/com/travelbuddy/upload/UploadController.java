package com.travelbuddy.upload;

import com.travelbuddy.common.exception.userinput.UserInputException;
import com.travelbuddy.common.utils.FilenameUtils;
import com.travelbuddy.upload.cloud.StorageService;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import com.travelbuddy.upload.cloud.exception.UploadFileNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/upload")
public class UploadController {
    private final StorageService storageService;

    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<List<FileRspnDto>> upload(@RequestParam("files") List<MultipartFile> files) throws IOException {
//                                              @RequestParam(value = "temporary", required = false, defaultValue = "true") boolean temporary) throws IOException {
        if (files.isEmpty()) {
            throw new UserInputException("File is empty");
        }

        List<FileRspnDto> uploadedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            FileUploadRqstDto fileUploadRqstDto = new FileUploadRqstDto();
            fileUploadRqstDto.setInputStream(file.getInputStream());
            fileUploadRqstDto.setMimeType(file.getContentType());
            fileUploadRqstDto.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()).orElse(null));

            FileRspnDto uploadedFile = storageService.uploadFileTemp(fileUploadRqstDto);
            uploadedFiles.add(uploadedFile);
        }

        return ResponseEntity.ok(uploadedFiles);
//
//        FileUploadRqstDto fileUploadRqstDto = new FileUploadRqstDto();
//        fileUploadRqstDto.setInputStream(file.getInputStream());
//        fileUploadRqstDto.setFolder(folder);
//        fileUploadRqstDto.setMimeType(file.getContentType());
//        fileUploadRqstDto.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()).orElse(null));
//
//        FileRspnDto uploadedFile = temporary
//                ? storageService.uploadFileTemp(fileUploadRqstDto)
//                : storageService.uploadFile(fileUploadRqstDto);
//
//        return ResponseEntity.ok(uploadedFile);
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<FileRspnDto> getFileInfo(@PathVariable String fileId) {
        try {
            FileRspnDto uploadedFile = storageService.getFileData(fileId);
            return ResponseEntity.ok(uploadedFile);
        } catch (UploadFileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/file/{fileId}/permanent")
    public ResponseEntity<?> makeFilePermanent(@PathVariable String fileId) {
        storageService.makeFilePermanent(fileId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/file/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileId) {
        storageService.deleteFile(fileId);

        return ResponseEntity.ok().build();
    }
}
