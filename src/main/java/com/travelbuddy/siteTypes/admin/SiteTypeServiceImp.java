package com.travelbuddy.siteTypes.admin;

import com.travelbuddy.siteTypes.DualState;
import com.travelbuddy.siteTypes.dto.PostSiteTypeDto;
import com.travelbuddy.siteTypes.exception.SiteTypeExistedException;
import com.travelbuddy.siteTypes.user.SiteTypeEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class SiteTypeServiceImp implements SiteTypeService {
    private final SiteTypeRepository siteTypeRepository;

    public SiteTypeServiceImp(SiteTypeRepository siteTypeRepository) {
        this.siteTypeRepository = siteTypeRepository;
    }

    @Override
    public Integer createSiteType(PostSiteTypeDto postSiteTypeDto) {
        try {
            SiteTypeEntity siteType = new SiteTypeEntity();
            // Check if the site type already exists
            Optional<Integer> existedSiteTypeId = siteTypeRepository.findIdBySiteType(postSiteTypeDto.getSiteTypeName());
            if (existedSiteTypeId.isPresent()) {
                SiteTypeExistedException siteTypeExistedException = new SiteTypeExistedException();
                siteTypeExistedException.setExitedSiteTypeId(existedSiteTypeId.get());
                throw siteTypeExistedException;
            }
            siteType.setSiteType(postSiteTypeDto.getSiteTypeName());
            siteType.setDualState(DualState.valueOf(postSiteTypeDto.getMode()));
            return siteTypeRepository.save(siteType).getID();
        } catch (SiteTypeExistedException e) {
            throw e;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
