package com.resumeanalyzer.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resume_analyses")
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeFileName;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String resumeText;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String jobDescription;

    private double matchPercentage;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String extractedSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String missingSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String suggestions;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String education;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String experience;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String projects;

    private LocalDateTime createdAt;

    @PrePersist
    public void createDate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public String getExtractedSkills() {
        return extractedSkills;
    }

    public void setExtractedSkills(String extractedSkills) {
        this.extractedSkills = extractedSkills;
    }

    public String getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(String missingSkills) {
        this.missingSkills = missingSkills;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}