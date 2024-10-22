package com.travelbuddy.siteTypes.user;

import com.travelbuddy.siteTypes.DualState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TypeDef(
//        name = "dual_state",
//        typeClass = PostgreSQLEnumJdbcType.class
//)
@Entity
@Table(name = "site_types")
public class SiteTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @Column(name = "type_name")
    private String siteType;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "dual_state")
    private DualState dualState;
}
