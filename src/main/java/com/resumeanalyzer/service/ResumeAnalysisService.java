package com.resumeanalyzer.service;

import com.resumeanalyzer.dto.AnalysisResult;
import com.resumeanalyzer.entity.ResumeAnalysis;
import com.resumeanalyzer.repository.ResumeAnalysisRepository;

import org.springframework.stereotype.Service;

@Service
public class ResumeAnalysisService {

    private final AiAnalysisService aiAnalysisService;

    private final ResumeAnalysisRepository repository;

    public ResumeAnalysisService(
            AiAnalysisService aiAnalysisService,
            ResumeAnalysisRepository repository) {

        this.aiAnalysisService =
                aiAnalysisService;

        this.repository =
                repository;
    }

    public ResumeAnalysis analyzeAndSave(
            String fileName,
            String resumeText,
            String jobDescription) {

        AnalysisResult result =
                aiAnalysisService.analyze(
                        resumeText,
                        jobDescription
                );

        ResumeAnalysis analysis =
                new ResumeAnalysis();

        analysis.setResumeFileName(
                fileName
        );

        analysis.setResumeText(
                resumeText
        );

        analysis.setJobDescription(
                jobDescription
        );

        analysis.setMatchPercentage(
                result.matchPercentage()
        );

        analysis.setExtractedSkills(
                String.join(
                        ", ",
                        result.skills()
                )
        );

        analysis.setMissingSkills(
                String.join(
                        ", ",
                        result.missingSkills()
                )
        );

        analysis.setEducation(
                result.education()
        );

        analysis.setExperience(
                result.experience()
        );

        analysis.setProjects(
                result.projects()
        );

        analysis.setSuggestions(
                String.join(
                        " | ",
                        result.suggestions()
                )
        );

        return repository.save(
                analysis
        );
    }
}