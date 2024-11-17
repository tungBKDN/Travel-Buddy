package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.OpeningTimeEntity;
import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import com.travelbuddy.persistence.domain.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SiteBasicInfoRspnDto {
    private Integer siteId;
    private Integer siteVersionId;

    private Integer likeCount;
    private Integer dislikeCount;
    private String userReaction;

    // Owner informations
    private Integer ownerId;
    private String ownerUsername;
    private String ownerProfilePicture;

    // SiteVersion informations - main informations showing the user to view
    private String siteName;
    private double lat;
    private double lng;
    private String resolvedAddress;
    private String website;
    private String createdAt;
    private SiteTypeRspnDto siteType;

    public void mapSiteVersion(SiteVersionEntity siteVersion) {
        this.siteId = siteVersion.getSiteId();
        this.siteVersionId = siteVersion.getId();
        this.siteName = siteVersion.getSiteName();
        this.lat = siteVersion.getLat();
        this.lng = siteVersion.getLng();
        this.resolvedAddress = siteVersion.getResolvedAddress();
        this.website = siteVersion.getWebsite();
        this.createdAt = siteVersion.getCreatedAt().toString();

        // Mapping the siteType
        this.siteType = new SiteTypeRspnDto(siteVersion.getSiteType());
    }

    public void mapUser(UserEntity userEntity) {
        this.ownerId = userEntity.getId();
        this.ownerUsername = userEntity.getNickname();
        this.ownerProfilePicture = null;
    }

    public void mapView(SiteVersionEntity siteVersion, UserEntity userEntity) {
        mapSiteVersion(siteVersion);
        mapUser(userEntity);
    }
}
