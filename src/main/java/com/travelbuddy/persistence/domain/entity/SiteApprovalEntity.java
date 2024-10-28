package com.travelbuddy.persistence.domain.entity;

import com.travelbuddy.common.constants.ApprovalStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "site_approvals")
public class SiteApprovalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "site_version_id", nullable = false)
    private Integer siteVersionId;

    @Column(name = "status")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Enumerated(EnumType.STRING)
    private ApprovalStatusEnum status;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "approved_at")
    private Timestamp approvedAt;
}
