package com.travelbuddy.user;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.common.utils.FilenameUtils;
import com.travelbuddy.common.utils.RequestUtils;
import com.travelbuddy.persistence.domain.dto.account.user.*;
import com.travelbuddy.upload.cloud.StorageService;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;
import com.travelbuddy.upload.cloud.dto.FileUploadRqstDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RequestUtils requestUtils;
    private final StorageService storageService;

    @GetMapping("/detail")
    public ResponseEntity<UserDetailRspnDto> getUserDetail() {
        int userId = requestUtils.getUserIdCurrentRequest();

        return ResponseEntity.ok(userService.getUserDetail(userId));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ChgPasswordRqstDto chgPasswordRqstDto) {
        int userId = requestUtils.getUserIdCurrentRequest();
        userService.changePassword(userId, chgPasswordRqstDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/basic-info")
    public ResponseEntity<Void> updateBasicInfo(@RequestBody @Valid UserBasicInfoUpdateRqstDto userBasicInfoUpdateRqstDto) {
        int userId = requestUtils.getUserIdCurrentRequest();
        userService.updateBasicInfo(userId, userBasicInfoUpdateRqstDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/email")
    public ResponseEntity<Void> updateEmail(@RequestBody @Valid ChgEmailRqstDto chgEmailRqstDto) {
        int userId = requestUtils.getUserIdCurrentRequest();
        userService.updateEmail(userId, chgEmailRqstDto.getEmail());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/detail")
    public ResponseEntity<Void> updateDetail(@RequestBody @Valid UserDetailUpdateRqstDto userDetailUpdateRqstDto) {
        int userId = requestUtils.getUserIdCurrentRequest();
        userService.updateDetail(userId, userDetailUpdateRqstDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/avatar")
    public ResponseEntity<Void> updateAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        if (ObjectUtils.isEmpty(avatar)) {
            throw new IllegalArgumentException("Avatar is required");
        }

        String mimeType = avatar.getContentType();
        if (StringUtils.isEmpty(mimeType) || !mimeType.startsWith("image")) {
            throw new IllegalArgumentException("Avatar must be an image");
        }

        int userId = requestUtils.getUserIdCurrentRequest();

        FileUploadRqstDto fileUploadRqstDto = FileUploadRqstDto.builder()
                .inputStream(avatar.getInputStream())
                .folder("avatars")
                .mimeType(mimeType)
                .extension(FilenameUtils.getExtension(avatar.getOriginalFilename()).orElse(null))
                .build();

        FileRspnDto uploadedFile =  storageService.uploadFile(fileUploadRqstDto);

        userService.updateAvatar(userId, uploadedFile);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/unactivated")
    public ResponseEntity<Void> unactivated() {
        int userId = requestUtils.getUserIdCurrentRequest();
        userService.unactivated(userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPublicInfoRspnDto> getUserPublicInfo(@PathVariable int userId) {
        UserPublicInfoRspnDto userPublicInfo = userService.getUserPublicInfo(userId);

        return ResponseEntity.ok(userPublicInfo);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUsers(@RequestParam(name = "q", required = false, defaultValue = "") String userSearch,
                                                               @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        PageDto<UserSearchRspnDto> userSearchRspnDto = userService.searchUsers(userSearch, page);

        return ResponseEntity.ok(userSearchRspnDto);
    }
}
