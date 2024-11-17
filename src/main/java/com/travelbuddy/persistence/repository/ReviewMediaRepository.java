package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ReviewMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewMediaRepository extends JpaRepository<ReviewMediaEntity, Long> {
    void deleteByMediaIdIn(List<String> mediaIdsToDelete);

    void deleteByReviewId(Integer id);
}
