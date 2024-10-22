package com.travelbuddy.siteTypes.dto;

import com.travelbuddy.siteTypes.DualState;
import com.travelbuddy.siteTypes.user.SiteTypeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseAllSiteTypes {
    private List<ResponseSiteType> siteTypes;
    private Integer total;

    public ResponseAllSiteTypes() {
        this.siteTypes = new ArrayList<ResponseSiteType>();
        this.total = 0;
    }

    public ResponseAllSiteTypes(List<SiteTypeEntity> siteTypeEntities) {
        this.total = siteTypeEntities.size();
        if (siteTypeEntities.isEmpty()) {
            this.siteTypes = new ArrayList<ResponseSiteType>();
            return;
        }
        siteTypes = new ArrayList<>();
        siteTypeEntities.forEach(siteTypeEntity -> {
            siteTypes.add(new ResponseSiteType(siteTypeEntity));
        });
    }
}

@Getter
@Setter
class ResponseSiteType {
    private Integer ID;
    private String name;
    private boolean isAtraction;
    private boolean isUltility;

    public ResponseSiteType(SiteTypeEntity siteTypeEntity) {
        this.ID = siteTypeEntity.getID();
        this.name = siteTypeEntity.getSiteType();
        this.isAtraction = true;
        this.isUltility = true;
        if (siteTypeEntity.getDualState() == DualState.ATTRACTION) {
            this.isUltility = false;
        } else {
            this.isAtraction = false;
        }
    }
}
