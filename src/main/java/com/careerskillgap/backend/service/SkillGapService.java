
package com.careerskillgap.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careerskillgap.backend.entity.CareerSkill;
import com.careerskillgap.backend.entity.SkillGap;
import com.careerskillgap.backend.entity.TechnicalSkill;
import com.careerskillgap.backend.repository.CareerSkillRepository;
import com.careerskillgap.backend.repository.SkillGapRepository;
import com.careerskillgap.backend.repository.TechnicalSkillRepository;

@Service
public class SkillGapService {

    private final CareerSkillRepository careerSkillRepository;
    private final TechnicalSkillRepository technicalSkillRepository;
    private final SkillGapRepository skillGapRepository;

    public SkillGapService(
            CareerSkillRepository careerSkillRepository,
            TechnicalSkillRepository technicalSkillRepository,
            SkillGapRepository skillGapRepository) {

        this.careerSkillRepository = careerSkillRepository;
        this.technicalSkillRepository = technicalSkillRepository;
        this.skillGapRepository = skillGapRepository;
    }

    @Transactional
    public List<SkillGap> analyzeSkillGap(String career, Long userId) {

        if (career == null || career.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Career name cannot be empty.");
        }

        if (userId == null) {
            throw new IllegalArgumentException(
                    "User ID cannot be null.");
        }

        String selectedCareer = career.trim();

        List<CareerSkill> requiredSkills =
                careerSkillRepository.findByCareer(selectedCareer);

        List<TechnicalSkill> studentSkills =
                technicalSkillRepository.findByUserId(userId);

        List<SkillGap> results = new ArrayList<>();

        for (CareerSkill requiredSkill : requiredSkills) {

            if (requiredSkill.getSkill() == null
                    || requiredSkill.getSkill().trim().isEmpty()) {
                continue;
            }

            String requiredSkillName =
                    requiredSkill.getSkill().trim();

            boolean skillAvailable = false;

            for (TechnicalSkill studentSkill : studentSkills) {

                if (studentSkill.getSkill() != null
                        && studentSkill.getSkill().trim()
                                .equalsIgnoreCase(requiredSkillName)) {

                    skillAvailable = true;
                    break;
                }
            }

            SkillGap skillGap = new SkillGap();
            skillGap.setCareer(selectedCareer);
            skillGap.setSkill(requiredSkillName);
            skillGap.setStatus(
                    skillAvailable ? "AVAILABLE" : "MISSING");

            results.add(skillGap);
        }

        // Remove previous results for this user's selected career,
        // then save the newly calculated results.
        skillGapRepository.deleteByCareer(selectedCareer);

        return skillGapRepository.saveAll(results);
    }
}