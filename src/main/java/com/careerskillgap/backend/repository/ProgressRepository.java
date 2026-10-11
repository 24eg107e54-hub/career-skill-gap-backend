package com.careerskillgap.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerskillgap.backend.entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByUserId(Long userId);

    Optional<Progress> findByUserIdAndSkill(Long userId, String skill);
}