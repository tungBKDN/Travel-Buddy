package com.travelbuddy.persistence.domain.dto.site;

import com.travelbuddy.persistence.domain.entity.SiteApprovalEntity;
import com.travelbuddy.persistence.domain.entity.SiteMediaEntity;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import com.travelbuddy.persistence.domain.entity.SiteVersionEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class SiteStatusRspndDto {
    private Integer siteId;
    private Integer siteVersionId;
    private String siteName;
    private String address;
    private String description;
    private double lat;
    private double lng;
    private String state; // PENDING, APPROVED, REJECTED
    private String createdAt;
    private String actionAppliedAt; // null if state is PENDING
    private String pictureUrl;
    private String typeOfModification;

    public SiteStatusRspndDto(SiteVersionEntity sv, SiteApprovalEntity sa, SiteMediaEntity sm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.siteId = sv.getSiteId();
        this.siteVersionId = sv.getId();
        this.siteName = sv.getSiteName();
        this.address = sv.getResolvedAddress();
        this.description = sv.getDescription();
        this.lat = sv.getLat();
        this.lng = sv.getLng();
        this.createdAt = sv.getCreatedAt().format(formatter); // 2024-09-01 09:21:13
        this.state = (sa.getStatus() == null) ? "PENDING" : sa.getStatus().name();
        this.actionAppliedAt = (sa.getApprovedAt() != null) ? sa.getApprovedAt().toString().formatted(formatter) : null;
        this.pictureUrl = (sm != null ? sm.getMedia().getUrl() : null);
        this.typeOfModification = (sv.getParentVersionId() == null) ? "NEW" : "MODIFIED";
    }
}
