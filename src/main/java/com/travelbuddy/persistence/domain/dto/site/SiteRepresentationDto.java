package com.travelbuddy.persistence.domain.dto.site;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.travelbuddy.persistence.domain.dto.fee.FeeRspndDto;
import com.travelbuddy.persistence.domain.dto.sitereview.MediaRspnDto;
import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SiteRepresentationDto implements Serializable {
    // Site informations
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
    private List<String> phoneNumbers;
    private List<GroupedSiteServicesRspnDto> groupedServices;
    private List<OpeningTimeRepresentationDto> openingTimes;
    private List<FeeRspndDto> fees;
    private String description;

    private List<MediaRspnDto> medias;

    private Double averageRating;
    private Integer totalRating;
    private Integer fiveStarRating;
    private Integer fourStarRating;
    private Integer threeStarRating;
    private Integer twoStarRating;
    private Integer oneStarRating;

    public void mapSiteVersion(SiteVersionEntity siteVersion) {
        this.siteId = siteVersion.getSiteId();
        this.siteVersionId = siteVersion.getId();
        this.siteName = siteVersion.getSiteName();
        this.lat = siteVersion.getLat();
        this.lng = siteVersion.getLng();
        this.resolvedAddress = siteVersion.getResolvedAddress();
        this.website = siteVersion.getWebsite();
        this.createdAt = siteVersion.getCreatedAt().toString();
        this.description = siteVersion.getDescription();
        // Mapping the siteType
        this.siteType = new SiteTypeRspnDto(siteVersion.getSiteType());
    }

    public void mapUser(UserEntity userEntity) {
        this.ownerId = userEntity.getId();
        this.ownerUsername = userEntity.getNickname();
        this.ownerProfilePicture = userEntity.getAvatar() != null ? userEntity.getAvatar().getUrl() : null;
    }

    public void mapView(SiteVersionEntity siteVersion, UserEntity userEntity, List<GroupedSiteServicesRspnDto> services, List<String> phoneNumbers, List<OpeningTimeEntity> openingTimes) {
        mapSiteVersion(siteVersion);
        mapUser(userEntity);
        this.groupedServices = services;
        this.phoneNumbers = phoneNumbers;
        this.openingTimes = openingTimes.stream()
                .map(OpeningTimeRepresentationDto::new)
                .toList();
    }
}
