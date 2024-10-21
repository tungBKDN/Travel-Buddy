package com.travelbuddy.auth.permission;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "permissions")
@Data
@EqualsAndHashCode
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "permissionEntities")
    private Set<GroupEntity> groupEntities;
}