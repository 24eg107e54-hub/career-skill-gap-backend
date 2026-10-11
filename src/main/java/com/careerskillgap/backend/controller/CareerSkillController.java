
package com.careerskillgap.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerskillgap.backend.entity.CareerSkill;
import com.careerskillgap.backend.repository.CareerSkillRepository;

@RestController
@RequestMapping("/api/career-skills")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174"
})
public class CareerSkillController {

    private final CareerSkillRepository careerSkillRepository;

    public CareerSkillController(CareerSkillRepository careerSkillRepository) {
        this.careerSkillRepository = careerSkillRepository;
    }

    @PostMapping
    public CareerSkill saveCareerSkill(@RequestBody CareerSkill careerSkill) {
        return careerSkillRepository.save(careerSkill);
    }

    @GetMapping("/{career}")
    public List<CareerSkill> getCareerSkills(@PathVariable String career) {
        return careerSkillRepository.findByCareer(career);
    }

    @GetMapping("/available-careers")
    public List<String> getAvailableCareers() {
        return careerSkillRepository.findDistinctCareers();
    }
}