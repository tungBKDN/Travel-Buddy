package com.travelbuddy.persistence.domain.dto.account.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPublicInfoRspnDto {
    private String nickname;
    private String fullName;
    private String gender;
    private String avatar;
    private String socialUrl;
    private Double score;
    private String createdAt;
}
