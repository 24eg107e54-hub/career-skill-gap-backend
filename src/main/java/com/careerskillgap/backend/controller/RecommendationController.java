package com.careerskillgap.backend.controller;

import com.careerskillgap.backend.entity.Recommendation;
import com.careerskillgap.backend.repository.RecommendationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {

    private final RecommendationRepository recommendationRepository;

    public RecommendationController(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @PostMapping
    public Recommendation saveRecommendation(
            @RequestBody Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @GetMapping
    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll();
    }
}
