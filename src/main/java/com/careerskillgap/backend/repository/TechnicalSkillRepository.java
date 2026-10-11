package com.careerskillgap.backend.repository;

import com.careerskillgap.backend.entity.TechnicalSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnicalSkillRepository extends JpaRepository<TechnicalSkill, Long> {

    List<TechnicalSkill> findByUserId(Long userId);
}