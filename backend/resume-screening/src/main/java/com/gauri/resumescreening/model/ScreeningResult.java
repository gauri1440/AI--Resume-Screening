package com.gauri.resumescreening.model;

public class ScreeningResult {

    private String candidateName;
    private double score;
    private String status;

    public ScreeningResult() {
    }

    public ScreeningResult(String candidateName, double score, String status) {
        this.candidateName = candidateName;
        this.score = score;
        this.status = status;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}