
package com.careerskillgap.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerskillgap.backend.entity.Education;
import com.careerskillgap.backend.repository.EducationRepository;

@RestController
@RequestMapping("/api/education")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174"
})
public class EducationController {

    private final EducationRepository educationRepository;

    public EducationController(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @PostMapping
    public Education saveEducation(@RequestBody Education education) {
        return educationRepository.save(education);
    }

    @GetMapping
    public List<Education> getEducationByUserId(
            @RequestParam Long userId) {
        return educationRepository.findByUserId(userId);
    }
}