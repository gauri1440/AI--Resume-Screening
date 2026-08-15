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

        Set<String> resumeSkills = convertToSet(resume.getSkills());

        Set<String> mandatorySkills =
                convertToSet(jobRequirement.getMandatorySkills());

        Set<String> requiredSkills =
                convertToSet(jobRequirement.getRequiredSkills());

        Set<String> preferredSkills =
                convertToSet(jobRequirement.getPreferredSkills());

        int mandatoryMatched = countMatched(resumeSkills, mandatorySkills);
        int requiredMatched = countMatched(resumeSkills, requiredSkills);
        int preferredMatched = countMatched(resumeSkills, preferredSkills);

        // Mandatory skill missing → Not shortlisted
        if (mandatoryMatched < mandatorySkills.size()) {

            return new ScreeningResult(
                    resume.getName(),
                    0,
                    "NOT SHORTLISTED",
                    mandatoryMatched,
                    requiredMatched,
                    preferredMatched
            );
        }

        // Required skills score
        double requiredScore = requiredSkills.isEmpty()
                ? 0
                : ((double) requiredMatched / requiredSkills.size()) * 70;

        // Preferred skills bonus
        double preferredScore = preferredSkills.isEmpty()
                ? 0
                : ((double) preferredMatched / preferredSkills.size()) * 30;

        double score = requiredScore + preferredScore;

        String status = score >= 70
                ? "SHORTLISTED"
                : "NOT SHORTLISTED";

        return new ScreeningResult(
                resume.getName(),
                score,
                status,
                mandatoryMatched,
                requiredMatched,
                preferredMatched
        );
    }

    private Set<String> convertToSet(String skills) {

        if (skills == null || skills.isBlank()) {
            return new HashSet<>();
        }

        Set<String> skillSet = new HashSet<>();

        Arrays.stream(skills.toLowerCase().split(","))
                .map(String::trim)
                .filter(skill -> !skill.isEmpty())
                .forEach(skillSet::add);

        return skillSet;
    }

    private int countMatched(
            Set<String> resumeSkills,
            Set<String> requiredSkills) {

        int count = 0;

        for (String skill : requiredSkills) {

            if (resumeSkills.contains(skill)) {
                count++;
            }
        }

        return count;
    }
}