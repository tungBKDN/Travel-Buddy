package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "fees")
public class FeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "site_version_id")
    private Integer siteVersionId;

    @Column(name = "aspect_id")
    private Integer aspectId;

    @Column(name = "fee_low")
    private Integer feeLow;

    @Column(name = "fee_high")
    private Integer feeHigh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_version_id", insertable = false, updatable = false)
    private SiteVersionEntity siteVersion;
}
