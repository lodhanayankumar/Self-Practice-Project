package com.resumeanalyzer.dto;

import java.util.List;

public record AnalysisResult(

        double matchPercentage,

        List<String> skills,

        List<String> missingSkills,

        String education,

        String experience,

        String projects,

        List<String> suggestions,

        String summary

) {
}