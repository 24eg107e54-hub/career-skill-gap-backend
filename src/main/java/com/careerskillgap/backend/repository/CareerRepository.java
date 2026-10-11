
package com.careerskillgap.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerskillgap.backend.entity.Career;

public interface CareerRepository extends JpaRepository<Career, Long> {

    List<Career> findByUserId(Long userId);

    Optional<Career> findByUserIdAndCareerNameIgnoreCase(
            Long userId, String careerName);

}