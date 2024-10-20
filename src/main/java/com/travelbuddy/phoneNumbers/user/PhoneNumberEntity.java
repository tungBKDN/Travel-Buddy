package com.travelbuddy.phoneNumbers.user;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_numbers")
public class PhoneNumberEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "site_versions_id")
    private Integer siteVersionID;

    @Column(name = "phone_number")
    private String phoneNumber;
}
