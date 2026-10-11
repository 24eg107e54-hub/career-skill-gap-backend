package com.careerskillgap.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerskillgap.backend.entity.Progress;
import com.careerskillgap.backend.repository.ProgressRepository;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class ProgressController {

    private final ProgressRepository progressRepository;

    public ProgressController(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @PostMapping
    public Progress saveProgress(@RequestBody Progress progress) {

        if (progress.getUserId() == null || progress.getSkill() == null
                || progress.getSkill().isBlank()) {
            throw new IllegalArgumentException(
                    "User ID and skill are required"
            );
        }

        Progress existing = progressRepository
                .findByUserIdAndSkill(
                        progress.getUserId(),
                        progress.getSkill()
                )
                .orElse(new Progress());

        existing.setUserId(progress.getUserId());
        existing.setSkill(progress.getSkill());
        existing.setProgress(progress.getProgress());
        existing.setStatus(progress.getStatus());

        return progressRepository.save(existing);
    }

    @GetMapping
    public List<Progress> getProgress(@RequestParam Long userId) {
        return progressRepository.findByUserId(userId);
    }
}