package com.resumeanalyzer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.resumeanalyzer.dto.AnalysisResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AiAnalysisService {

    private final ObjectMapper objectMapper;

    private final RestClient restClient;

    @Value("${app.ai.api-key:}")
    private String apiKey;

    @Value("${app.ai.model:gpt-4.1-mini}")
    private String model;

    @Value("${app.ai.base-url:https://api.openai.com/v1}")
    private String baseUrl;

    public AiAnalysisService(
            ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;

        this.restClient =
                RestClient.builder().build();
    }

    public AnalysisResult analyze(
            String resumeText,
            String jobDescription) {

        if (apiKey == null ||
                apiKey.isBlank()) {

            return localAnalysis(
                    resumeText,
                    jobDescription
            );
        }

        try {

            return aiAnalysis(
                    resumeText,
                    jobDescription
            );

        } catch (Exception e) {

            System.out.println(
                    "AI API failed. Using local analysis."
            );

            System.out.println(
                    e.getMessage()
            );

            return localAnalysis(
                    resumeText,
                    jobDescription
            );
        }
    }

    private AnalysisResult aiAnalysis(
            String resumeText,
            String jobDescription)
            throws Exception {

        String prompt = """
                You are an expert resume analyzer.

                Analyze the resume against the job description.

                Return ONLY valid JSON.

                JSON format:

                {
                  "matchPercentage": 80,
                  "skills": ["Java", "Spring Boot"],
                  "missingSkills": ["Docker"],
                  "education": "Bachelor degree",
                  "experience": "Java developer",
                  "projects": "Student management project",
                  "suggestions": ["Learn Docker"],
                  "summary": "Good match"
                }

                Resume:
                %s

                Job Description:
                %s
                """.formatted(
                resumeText,
                jobDescription
        );

        String requestJson =
                """
                {
                  "model": "%s",
                  "input": [
                    {
                      "role": "user",
                      "content": [
                        {
                          "type": "input_text",
                          "text": %s
                        }
                      ]
                    }
                  ]
                }
                """.formatted(
                        model,
                        objectMapper.writeValueAsString(prompt)
                );

        String response =
                restClient.post()
                        .uri(
                                baseUrl
                                        + "/responses"
                        )
                        .header(
                                "Authorization",
                                "Bearer " + apiKey
                        )
                        .contentType(
                                MediaType.APPLICATION_JSON
                        )
                        .body(requestJson)
                        .retrieve()
                        .body(String.class);

        String text =
                extractResponseText(response);

        text = cleanJson(text);

        JsonNode node =
                objectMapper.readTree(text);

        return new AnalysisResult(

                node.path(
                        "matchPercentage"
                ).asDouble(),

                toList(
                        node.path("skills")
                ),

                toList(
                        node.path("missingSkills")
                ),

                node.path(
                        "education"
                ).asText(),

                node.path(
                        "experience"
                ).asText(),

                node.path(
                        "projects"
                ).asText(),

                toList(
                        node.path("suggestions")
                ),

                node.path(
                        "summary"
                ).asText()
        );
    }

    private String extractResponseText(
            String response)
            throws Exception {

        JsonNode root =
                objectMapper.readTree(response);

        if (root.has("output_text")) {

            return root
                    .path("output_text")
                    .asText();
        }

        JsonNode output =
                root.path("output");

        for (JsonNode item : output) {

            JsonNode content =
                    item.path("content");

            for (JsonNode c : content) {

                if (c.has("text")) {

                    return c
                            .path("text")
                            .asText();
                }
            }
        }

        throw new IllegalStateException(
                "AI response text not found."
        );
    }

    private String cleanJson(
            String text) {

        text = text.trim();

        if (text.startsWith("```")) {

            text = text
                    .replaceFirst(
                            "^```json",
                            ""
                    )
                    .replaceFirst(
                            "^```",
                            ""
                    );

            if (text.endsWith("```")) {

                text =
                        text.substring(
                                0,
                                text.length() - 3
                        );
            }
        }

        return text.trim();
    }

    private List<String> toList(
            JsonNode node) {

        List<String> list =
                new ArrayList<>();

        if (node.isArray()) {

            for (JsonNode item : node) {

                list.add(
                        item.asText()
                );
            }
        }

        return list;
    }

    private AnalysisResult localAnalysis(
            String resumeText,
            String jobDescription) {

        List<String> knownSkills =
                Arrays.asList(
                        "java",
                        "spring",
                        "spring boot",
                        "hibernate",
                        "jpa",
                        "jdbc",
                        "mysql",
                        "sql",
                        "html",
                        "css",
                        "javascript",
                        "react",
                        "angular",
                        "python",
                        "c++",
                        "git",
                        "github",
                        "docker",
                        "aws",
                        "rest api",
                        "maven"
                );

        String resume =
                resumeText.toLowerCase();

        String job =
                jobDescription.toLowerCase();

        List<String> resumeSkills =
                new ArrayList<>();

        List<String> requiredSkills =
                new ArrayList<>();

        for (String skill : knownSkills) {

            if (resume.contains(skill)) {

                resumeSkills.add(skill);
            }

            if (job.contains(skill)) {

                requiredSkills.add(skill);
            }
        }

        List<String> missing =
                new ArrayList<>(
                        requiredSkills
                );

        missing.removeAll(
                resumeSkills
        );

        double percentage = 0;

        if (!requiredSkills.isEmpty()) {

            percentage =
                    ((double)
                            (requiredSkills.size()
                                    - missing.size())
                            / requiredSkills.size())
                            * 100;
        }

        List<String> suggestions =
                new ArrayList<>();

        for (String skill : missing) {

            suggestions.add(
                    "Learn or improve "
                            + skill
            );
        }

        return new AnalysisResult(

                Math.round(
                        percentage * 100
                ) / 100.0,

                resumeSkills,

                missing,

                findSection(
                        resumeText,
                        "education"
                ),

                findSection(
                        resumeText,
                        "experience"
                ),

                findSection(
                        resumeText,
                        "project"
                ),

                suggestions,

                "Resume analyzed using "
                        + "skill matching."
        );
    }

    private String findSection(
            String text,
            String keyword) {

        String lower =
                text.toLowerCase();

        int index =
                lower.indexOf(keyword);

        if (index == -1) {

            return "Not detected";
        }

        int end =
                Math.min(
                        text.length(),
                        index + 500
                );

        return text.substring(
                index,
                end
        );
    }
}