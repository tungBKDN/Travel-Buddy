package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {
    List<FileEntity> findAllByCreatedAtBefore(LocalDateTime createdAt);

    List<FileEntity> findAllByIdIn(List<String> ids);
}
