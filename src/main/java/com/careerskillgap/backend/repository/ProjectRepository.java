package com.careerskillgap.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerskillgap.backend.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}