package com.travelbuddy.upload.cloud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TemporaryFileRepository extends JpaRepository<TemporaryFile, String> {
    List<TemporaryFile> findAllByUploadDateBefore(Date date);
}
