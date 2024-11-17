package com.travelbuddy.persistence.domain.dto.siteapproval;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSiteApprovalRqstDto {
    @NotNull
    private Integer id;

    @NotNull
    private String status;
    /*
    * Status must be in: ["APPROVED", "REJECTED"]
    */
}
