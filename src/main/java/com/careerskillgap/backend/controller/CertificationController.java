package com.careerskillgap.backend.controller;

import com.careerskillgap.backend.entity.Certification;
import com.careerskillgap.backend.repository.CertificationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
@CrossOrigin(origins = "*")
public class CertificationController {

    private final CertificationRepository certificationRepository;

    public CertificationController(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    @PostMapping
    public Certification saveCertification(@RequestBody Certification certification) {
        return certificationRepository.save(certification);
    }

    @GetMapping
    public List<Certification> getCertifications() {
        return certificationRepository.findAll();
    }
}