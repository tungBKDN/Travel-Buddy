package com.travelbuddy.user;

import com.travelbuddy.user.dto.ChgPasswordRqstDto;

public interface UserService {
    int getUserIdByEmailOrUsername(String emailOrUsername);

    boolean isUserExists(String emailOrUsername);

    void changePassword(int userId, ChgPasswordRqstDto chgPasswordRqstDto);
}
