
package com.careerskillgap.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.careerskillgap.backend.entity.Career;
import com.careerskillgap.backend.repository.CareerRepository;

@RestController
@RequestMapping("/api/careers")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class CareerController {

    private final CareerRepository careerRepository;

    public CareerController(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    @PostMapping
    public Career saveCareer(@RequestBody Career career) {
        if (career.getCareerName() == null ||
                career.getCareerName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Career name is required");
        }

        if (career.getUserId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User ID is required");
        }

        return careerRepository
                .findByUserIdAndCareerNameIgnoreCase(
                        career.getUserId(), career.getCareerName().trim())
                .orElseGet(() -> {
                    career.setCareerName(career.getCareerName().trim());
                    return careerRepository.save(career);
                });
    }

    @GetMapping
    public List<Career> getCareers() {
        return careerRepository.findAll()
                .stream()
                .filter(career -> career.getUserId() != null)
                .toList();
    }

    @GetMapping("/user/{userId}")
    public List<Career> getCareersByUserId(@PathVariable Long userId) {
        return careerRepository.findByUserId(userId);
    }
}