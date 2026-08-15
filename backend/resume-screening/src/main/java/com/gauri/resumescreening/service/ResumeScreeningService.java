package com.gauri.resumescreening.service;

import com.gauri.resumescreening.model.JobRequirement;
import com.gauri.resumescreening.model.Resume;
import com.gauri.resumescreening.model.ScreeningResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ResumeScreeningService {

    public ScreeningResult screenResume(
            Resume resume,
            JobRequirement jobRequirement) {

        Set<String> resumeSkills = new HashSet<>(
                Arrays.asList(resume.getSkills().toLowerCase().split(","))
        );

        Set<String> requiredSkills = new HashSet<>(
                Arrays.asList(jobRequirement.getRequiredSkills().toLowerCase().split(","))
        );

        int matchedSkills = 0;

        for (String skill : requiredSkills) {

            skill = skill.trim();

            if (resumeSkills.contains(skill)) {
                matchedSkills++;
            }
        }

        double score = ((double) matchedSkills / requiredSkills.size()) * 100;

        String status = score >= 70
                ? "SHORTLISTED"
                : "NOT SHORTLISTED";

        return new ScreeningResult(
                resume.getName(),
                score,
                status
        );
    }
}