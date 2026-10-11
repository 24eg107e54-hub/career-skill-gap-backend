
package com.careerskillgap.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.careerskillgap.backend.entity.SkillGap;

public interface SkillGapRepository extends JpaRepository<SkillGap, Long> {

    @Modifying
    @Query("DELETE FROM SkillGap s WHERE s.career = :career")
    void deleteByCareer(@Param("career") String career);
}