package com.gauri.resumescreening.model;

public class ScreeningRequest {

    private Resume resume;
    private JobRequirement jobRequirement;

    public ScreeningRequest() {
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public JobRequirement getJobRequirement() {
        return jobRequirement;
    }

    public void setJobRequirement(JobRequirement jobRequirement) {
        this.jobRequirement = jobRequirement;
    }
}