package com.travelbuddy.persistence.domain.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BasicInfoDto {
    private Integer id;
    private String email;
    private String nickname;
    private String fullName;
    private String avatar;
}
