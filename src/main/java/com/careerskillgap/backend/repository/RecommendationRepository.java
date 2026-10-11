package com.careerskillgap.backend.repository;

import com.careerskillgap.backend.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}