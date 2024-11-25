package com.travelbuddy.persistence.domain.dto.siteapproval;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GeneralViewSiteApprovalRspndDto {
    private Integer id;
    private String siteStatus;
    private ApprovalStatusEnum approvalStatus;

    private String siteName;
    private Integer siteVersionId;
    private String createdAt;

    public GeneralViewSiteApprovalRspndDto() {
    }

    public GeneralViewSiteApprovalRspndDto(SiteApprovalEntity approvalEntity, SiteVersionEntity siteVersion) {
        this.id = approvalEntity.getId();
        this.siteStatus = (siteVersion.getParentVersionId() == null) ? "NEW" : "MODIFY" ;
        this.approvalStatus = approvalEntity.getStatus();
        this.siteName = siteVersion.getSiteName();
        this.siteVersionId = siteVersion.getId();
        this.createdAt = siteVersion.getCreatedAt().toString();
    }

    public GeneralViewSiteApprovalRspndDto(SiteApprovalEntity approvalEntity) {
        this.id = approvalEntity.getId();
        this.siteStatus = (approvalEntity.getSiteVersion().getParentVersionId() == null) ? "NEW" : "MODIFY" ;
        this.approvalStatus = approvalEntity.getStatus();
        this.siteName = approvalEntity.getSiteVersion().getSiteName();
        this.siteVersionId = approvalEntity.getSiteVersion().getId();
        this.createdAt = approvalEntity.getSiteVersion().getCreatedAt().toString();
    }
}
