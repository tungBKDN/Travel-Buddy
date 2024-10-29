package com.travelbuddy.siteapproval.admin;

import com.travelbuddy.persistence.domain.dto.siteapproval.UpdateSiteApprovalRqstDto;

public interface SiteApprovalService {
    void createDefaultSiteApproval(Integer siteVersionId);
    void updateSiteApproval(UpdateSiteApprovalRqstDto updateSiteApprovalRqstDto, Integer adminId);
}
