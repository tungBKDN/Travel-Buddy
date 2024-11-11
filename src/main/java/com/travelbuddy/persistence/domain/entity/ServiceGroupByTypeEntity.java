package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "service_groups_by_types")
public class ServiceGroupByTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type_id", nullable = false)
    private Integer typeId;

    @Column(name = "service_group_id", nullable = false)
    private Integer serviceGroupId;

    // Lazy joins
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private SiteTypeEntity typeEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_group_id", insertable = false, updatable = false)
    private ServiceGroupEntity serviceGroupEntity;
}
