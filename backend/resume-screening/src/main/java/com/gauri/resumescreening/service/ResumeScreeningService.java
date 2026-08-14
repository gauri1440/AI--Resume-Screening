package com.gauri.resumescreening.service;

import com.gauri.resumescreening.model.JobRequirement;
import com.gauri.resumescreening.model.Resume;
import com.gauri.resumescreening.model.ScreeningResult;
import org.springframework.stereotype.Service;

@Service
public class ResumeScreeningService {

    public ScreeningResult screenResume(
            Resume resume,
            JobRequirement jobRequirement) {

        String resumeSkills = resume.getSkills().toLowerCase();
        String requiredSkills =
                jobRequirement.getRequiredSkills().toLowerCase();

        String[] skills = requiredSkills.split(",");

        int matchedSkills = 0;

        for (String skill : skills) {

            skill = skill.trim();

            if (resumeSkills.contains(skill)) {
                matchedSkills++;
            }
        }

        double score =
                ((double) matchedSkills / skills.length) * 100;

        String status;

        if (score >= 70) {
            status = "SHORTLISTED";
        } else {
            status = "NOT SHORTLISTED";
        }

        return new ScreeningResult(
                resume.getName(),
                score,
                status
        );
    }
}