package com.gauri.resumescreening.model;

public class JobRequirement {

    private String jobTitle;
    private String mandatorySkills;
    private String requiredSkills;
    private String preferredSkills;

    public JobRequirement() {
    }

    public JobRequirement(String jobTitle,
                          String mandatorySkills,
                          String requiredSkills,
                          String preferredSkills) {

        this.jobTitle = jobTitle;
        this.mandatorySkills = mandatorySkills;
        this.requiredSkills = requiredSkills;
        this.preferredSkills = preferredSkills;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMandatorySkills() {
        return mandatorySkills;
    }

    public void setMandatorySkills(String mandatorySkills) {
        this.mandatorySkills = mandatorySkills;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getPreferredSkills() {
        return preferredSkills;
    }

    public void setPreferredSkills(String preferredSkills) {
        this.preferredSkills = preferredSkills;
    }
}