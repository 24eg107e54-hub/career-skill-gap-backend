
package com.careerskillgap.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.careerskillgap.backend.entity.CareerSkill;

public interface CareerSkillRepository extends JpaRepository<CareerSkill, Long> {

    List<CareerSkill> findByCareer(String career);

    @Query("SELECT DISTINCT c.career FROM CareerSkill c ORDER BY c.career")
    List<String> findDistinctCareers();
}