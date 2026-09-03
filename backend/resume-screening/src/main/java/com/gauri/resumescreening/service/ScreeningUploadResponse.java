package com.example.resume_screening.dto;

import java.util.List;

public class ScreeningUploadResponse {

    private String name;
    private String email;
    private double score;
    private String status;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public ScreeningUploadResponse(
            String name,
            String email,
            double score,
            String status,
            List<String> matchedSkills,
            List<String> missingSkills) {

        this.name = name;
        this.email = email;
        this.score = score;
        this.status = status;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }
}