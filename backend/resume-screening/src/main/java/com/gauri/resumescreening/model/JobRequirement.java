package com.gauri.resumescreening.model;

public class JobRequirement {

    private String jobTitle;
    private String requiredSkills;

    public JobRequirement() {
    }

    public JobRequirement(String jobTitle, String requiredSkills) {
        this.jobTitle = jobTitle;
        this.requiredSkills = requiredSkills;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}