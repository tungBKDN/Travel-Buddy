package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "aspects_by_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AspectsByTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_id")
    private Integer typeId;

    @NotBlank
    @Column(name = "aspect")
    private String aspectName;
}
