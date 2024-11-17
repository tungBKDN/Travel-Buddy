package com.travelbuddy.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "token_store")
public class TokenStoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    private String token;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

    @PrePersist
    public void prePersist() {
        this.expiryDate = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30);
    }
}
