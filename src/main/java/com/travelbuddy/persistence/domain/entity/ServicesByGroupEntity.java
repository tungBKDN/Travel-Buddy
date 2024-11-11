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
@Table(name = "services_by_groups")
public class ServicesByGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "service_group_id")
    private Integer serviceGroupId;

    // EAGER JOIN
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private ServiceEntity serviceEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_group_id", insertable = false, updatable = false)
    private ServiceGroupEntity serviceGroupEntity;
}
