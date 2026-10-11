
package com.careerskillgap.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerskillgap.backend.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

    List<Education> findByUserId(Long userId);

}