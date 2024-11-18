package com.travelbuddy.persistence.domain.dto.account.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDetailRspnDto {
    private Integer id;
    private String nickname;
    private String fullName;
    private String email;
    private String gender;
    private String phoneNumber;
    private String avatar;
    private String createdAt;
}
