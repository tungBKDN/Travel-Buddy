package com.travelbuddy.siteapproval.admin;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import com.travelbuddy.persistence.domain.dto.siteapproval.UpdateSiteApprovalRqstDto;
import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import com.travelbuddy.persistence.repository.SiteApprovalRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class SiteApprovalServiceImp implements SiteApprovalService {
    private final SiteApprovalRepository siteApprovalRepository;

    public SiteApprovalServiceImp(SiteApprovalRepository siteApprovalRepository) {
        this.siteApprovalRepository = siteApprovalRepository;
    }

    @Override
    public void createDefaultSiteApproval(Integer siteVersionId) {
        /*
        * This function creates default site_approval record when a new site is created
        */
        SiteApprovalEntity siteApprovalEntity = new SiteApprovalEntity();
        siteApprovalEntity = SiteApprovalEntity.builder().siteVersionId(siteVersionId).build();

        // Save the site approval
        siteApprovalRepository.save(siteApprovalEntity);
    }

    @Override
    public void updateSiteApproval(UpdateSiteApprovalRqstDto updateSiteApprovalRqstDto, Integer adminId) {
        /*
        * This function updates the site approval record when admin approves or rejects a site
        * with adminId get from the JWTToken
        */
        SiteApprovalEntity siteApprovalEntity = siteApprovalRepository.findById(updateSiteApprovalRqstDto.getId()).orElseThrow(() -> new RuntimeException("Site approval not found"));
        siteApprovalEntity.setStatus(ApprovalStatusEnum.valueOf(updateSiteApprovalRqstDto.getStatus()));
        siteApprovalEntity.setAdminId(adminId);
        siteApprovalEntity.setApprovedAt(Timestamp.valueOf(LocalDateTime.now()));
        siteApprovalRepository.save(siteApprovalEntity);
    }
}
