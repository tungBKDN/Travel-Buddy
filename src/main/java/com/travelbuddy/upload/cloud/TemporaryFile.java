package com.travelbuddy.upload.cloud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temporary_files")
public class TemporaryFile {
    @Id
    private String id;

    private Date uploadDate = new Date();
}
