package com.gauri.resumescreening.model;

public class ScreeningResult {

    private String candidateName;
    private double score;
    private String status;
    private int mandatoryMatched;
    private int requiredMatched;
    private int preferredMatched;

    public ScreeningResult() {
    }

    public ScreeningResult(String candidateName,
                           double score,
                           String status,
                           int mandatoryMatched,
                           int requiredMatched,
                           int preferredMatched) {

        this.candidateName = candidateName;
        this.score = score;
        this.status = status;
        this.mandatoryMatched = mandatoryMatched;
        this.requiredMatched = requiredMatched;
        this.preferredMatched = preferredMatched;
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

    public int getMandatoryMatched() {
        return mandatoryMatched;
    }

    public void setMandatoryMatched(int mandatoryMatched) {
        this.mandatoryMatched = mandatoryMatched;
    }

    public int getRequiredMatched() {
        return requiredMatched;
    }

    public void setRequiredMatched(int requiredMatched) {
        this.requiredMatched = requiredMatched;
    }

    public int getPreferredMatched() {
        return preferredMatched;
    }

    public void setPreferredMatched(int preferredMatched) {
        this.preferredMatched = preferredMatched;
    }
}