package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "files")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "admin", "reviewMedia", "siteMedia", "travelPlan"})
public class FileEntity {
    @Id
    private String id;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "avatar", fetch = FetchType.LAZY)
    private UserEntity user;

    @OneToOne(mappedBy = "avatar", fetch = FetchType.LAZY)
    private AdminEntity admin;

    @OneToOne(mappedBy = "cover", fetch = FetchType.LAZY)
    private TravelPlanEntity travelPlan;

    @OneToOne(mappedBy = "media", fetch = FetchType.LAZY)
    private ReviewMediaEntity reviewMedia;

    @OneToOne(mappedBy = "media", fetch = FetchType.LAZY)
    private SiteMediaEntity siteMedia;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
