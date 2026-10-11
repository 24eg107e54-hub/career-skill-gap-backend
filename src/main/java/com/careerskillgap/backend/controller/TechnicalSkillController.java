package com.careerskillgap.backend.controller;

import com.careerskillgap.backend.entity.TechnicalSkill;
import com.careerskillgap.backend.repository.TechnicalSkillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class TechnicalSkillController {

    private final TechnicalSkillRepository skillRepository;

    public TechnicalSkillController(TechnicalSkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @PostMapping
    public TechnicalSkill saveSkill(@RequestBody TechnicalSkill skill) {
        return skillRepository.save(skill);
    }

    @GetMapping
    public List<TechnicalSkill> getSkills() {
        return skillRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<TechnicalSkill> getSkillsByUser(
            @PathVariable Long userId) {

        return skillRepository.findByUserId(userId);
    }
}