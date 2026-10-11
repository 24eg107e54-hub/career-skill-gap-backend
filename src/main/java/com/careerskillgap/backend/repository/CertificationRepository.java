package com.careerskillgap.backend.repository;

import com.careerskillgap.backend.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}