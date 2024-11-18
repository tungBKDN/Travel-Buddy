package com.travelbuddy.persistence.domain.dto.account.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRspnDto {
    private Integer id;
    private String nickname;
    private String fullName;
    private String avatar;
}
