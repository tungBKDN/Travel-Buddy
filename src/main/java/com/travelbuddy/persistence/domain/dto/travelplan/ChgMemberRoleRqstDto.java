package com.travelbuddy.persistence.domain.dto.travelplan;

import lombok.Data;

import java.util.List;

@Data
public class ChgMemberRoleRqstDto {
    private int userId;
    private String role;
}
