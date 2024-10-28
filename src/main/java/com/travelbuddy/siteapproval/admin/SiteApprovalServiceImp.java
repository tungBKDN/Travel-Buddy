package com.travelbuddy.siteapproval.admin;

import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import com.travelbuddy.persistence.repository.SiteApprovalRepository;
import org.springframework.stereotype.Service;

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
}
