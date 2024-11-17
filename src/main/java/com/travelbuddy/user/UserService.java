package com.travelbuddy.user;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.persistence.domain.dto.account.user.*;
import com.travelbuddy.persistence.domain.dto.auth.BasicInfoDto;
import com.travelbuddy.upload.cloud.dto.FileRspnDto;

import java.util.List;

public interface UserService {
    int getUserIdByEmailOrUsername(String emailOrUsername);

    boolean isUserExists(String emailOrUsername);

    void changePassword(int userId, ChgPasswordRqstDto chgPasswordRqstDto);

    UserDetailRspnDto getUserDetail(int userId);

    void updateBasicInfo(int userId, UserBasicInfoUpdateRqstDto userBasicInfoUpdateRqstDto);

    void updateDetail(int userId, UserDetailUpdateRqstDto userDetailUpdateRqstDto);

    void updateAvatar(int userId, FileRspnDto uploadedFile);

    void unactivated(int userId);

    BasicInfoDto getUserBasicInfo(String emailOrUsername);

    UserPublicInfoRspnDto getUserPublicInfo(int userId);

    PageDto<UserSearchRspnDto> searchUsers(String userSearch, int page);


    void updateEmail(int userId, String email);
}
