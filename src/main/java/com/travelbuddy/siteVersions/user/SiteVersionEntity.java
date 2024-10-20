package com.travelbuddy.siteVersions.user;

import com.travelbuddy.sites.dto.PostSiteDto;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "site_versions")
public class SiteVersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "site_id")
    private Integer siteID;

    @Column(name = "parent_version_id")
    private Integer parentVersionID;

    @Column(name = "site_name")
    private String siteName;

    private double lat;
    private double lon;

    @Column(name = "resolved_address")
    private String resolvedAddress;

    private String website;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "type_id")
    private Integer typeID;

//    public SiteVersionEntity parse(PostSiteDto postSiteDto) {
//
//    }
}
