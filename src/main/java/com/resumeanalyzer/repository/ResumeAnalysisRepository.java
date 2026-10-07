package com.resumeanalyzer.repository;

import com.resumeanalyzer.entity.ResumeAnalysis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {

}