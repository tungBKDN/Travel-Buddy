package com.travelbuddy.persistence.repository;

import com.travelbuddy.persistence.domain.entity.ReviewReactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewReactionRepository extends JpaRepository<ReviewReactionEntity, Long> {
    Optional<ReviewReactionEntity> findByUserIdAndReviewId(int userId, int reviewId);
}
