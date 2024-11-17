package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "site_medias")
@ToString(exclude = {"site", "media"})
public class SiteMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "media_type")
    private String mediaType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private SiteEntity site;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private FileEntity media;
}
