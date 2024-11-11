package com.travelbuddy.persistence.domain.dto.site;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.travelbuddy.persistence.domain.dto.siteservice.GroupedSiteServicesRspnDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.PhoneNumberEntity;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import com.travelbuddy.persistence.domain.entity.UserEntity;
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

    public void mapSiteVersion(SiteVersionEntity siteVersion) {
        this.siteId = siteVersion.getSiteId();
        this.siteVersionId = siteVersion.getId();
        this.siteName = siteVersion.getSiteName();
        this.lat = siteVersion.getLat();
        this.lng = siteVersion.getLng();
        this.resolvedAddress = siteVersion.getResolvedAddress();
        this.website = siteVersion.getWebsite();
        this.createdAt = siteVersion.getCreatedAt().toString();

        Hibernate.initialize(siteVersion.getOpeningTimes());
        // Mapping the siteType
        this.siteType = new SiteTypeRspnDto();
        this.siteType.mapFromSiteTypeEntity(siteVersion.getSiteType());

        Hibernate.initialize(siteVersion.getOpeningTimes());
        this.openingTimes = siteVersion.getOpeningTimes().stream()
                .map(OpeningTimeRepresentationDto::new)
                .toList();

        Hibernate.initialize(siteVersion.getPhoneNumbers());
        this.phoneNumbers = siteVersion.getPhoneNumbers().stream()
                .map(PhoneNumberEntity::getPhoneNumber)
                .toList();
    }

    public void mapUser(UserEntity userEntity) {
        this.ownerId = userEntity.getId();
        this.ownerUsername = userEntity.getUsername();
        this.ownerProfilePicture = null;
    }

    public void mapView(SiteVersionEntity siteVersion, UserEntity userEntity, List<GroupedSiteServicesRspnDto> services) {
        mapSiteVersion(siteVersion);
        mapUser(userEntity);
        this.groupedServices = services;
    }
}
