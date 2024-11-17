package com.travelbuddy.persistence.domain.dto.account.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailRspnDto {
    private Integer id;
    private String nickname;
    private String fullName;
    private String email;
    private String gender;
    private String socialUrl;
    private String avatar;
    private Double score;
    private String createdAt;
}
