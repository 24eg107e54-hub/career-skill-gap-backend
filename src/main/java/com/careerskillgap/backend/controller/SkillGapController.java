
package com.careerskillgap.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerskillgap.backend.entity.SkillGap;
import com.careerskillgap.backend.service.SkillGapService;

@RestController
@RequestMapping("/api/skill-gap")
@CrossOrigin(origins = "*")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @GetMapping("/analyze")
    public List<SkillGap> analyzeSkillGap(
            @RequestParam String career,
            @RequestParam Long userId) {

        return skillGapService.analyzeSkillGap(career, userId);
    }
}