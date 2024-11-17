package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "services_by_site_versions")
public class ServicesBySiteVersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "site_version_id")
    private Integer siteVersionId;

    @Column(name = "service_id")
    private Integer serviceId;

    // Easy joins
    @ManyToOne
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private ServiceEntity serviceEntity;
}