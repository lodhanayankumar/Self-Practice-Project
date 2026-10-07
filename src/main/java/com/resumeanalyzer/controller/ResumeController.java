package com.resumeanalyzer.controller;

import com.resumeanalyzer.entity.ResumeAnalysis;
import com.resumeanalyzer.repository.ResumeAnalysisRepository;
import com.resumeanalyzer.service.ResumeAnalysisService;
import com.resumeanalyzer.service.ResumeTextExtractor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin("*")
public class ResumeController {

    private final ResumeTextExtractor extractor;

    private final ResumeAnalysisService analysisService;

    private final ResumeAnalysisRepository repository;

    public ResumeController(
            ResumeTextExtractor extractor,
            ResumeAnalysisService analysisService,
            ResumeAnalysisRepository repository) {

        this.extractor = extractor;

        this.analysisService =
                analysisService;

        this.repository =
                repository;
    }

    @PostMapping(
            value = "/analyze",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<?> analyzeResume(

            @RequestPart("resume")
            MultipartFile resume,

            @RequestPart("jobDescription")
            String jobDescription) {

        try {

            String resumeText =
                    extractor.extractText(
                            resume
                    );

            ResumeAnalysis result =
                    analysisService.analyzeAndSave(
                            resume.getOriginalFilename(),
                            resumeText,
                            jobDescription
                    );

            return ResponseEntity.ok(
                    result
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Error: "
                                    + e.getMessage()
                    );
        }
    }

    @PostMapping("/analyze-text")
    public ResponseEntity<?> analyzeText(

            @RequestBody TextRequest request) {

        try {

            ResumeAnalysis result =
                    analysisService.analyzeAndSave(
                            "typed-resume.txt",
                            request.resumeText(),
                            request.jobDescription()
                    );

            return ResponseEntity.ok(
                    result
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Error: "
                                    + e.getMessage()
                    );
        }
    }

    @GetMapping
    public List<ResumeAnalysis> getAllAnalyses() {

        return repository.findAll();
    }

    public record TextRequest(
            String resumeText,
            String jobDescription
    ) {
    }
}