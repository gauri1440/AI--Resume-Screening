
package com.gauri.resumescreening.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeTextExtractor {

    private static final List<String> COMMON_SKILLS = List.of(
            "Java",
            "Spring Boot",
            "Spring",
            "SQL",
            "MySQL",
            "PostgreSQL",
            "REST API",
            "React",
            "JavaScript",
            "HTML",
            "CSS",
            "Git",
            "GitHub",
            "Docker",
            "AWS",
            "Python",
            "C++",
            "C",
            "MongoDB",
            "Hibernate",
            "JPA",
            "Microservices"
    );

    public String extractName(String text) {

        String[] lines = text.split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (!line.isEmpty()
                    && !line.toLowerCase().contains("resume")
                    && !line.toLowerCase().contains("curriculum vitae")
                    && line.length() < 60) {

                return line;
            }
        }

        return "Unknown";
    }

    public String extractEmail(String text) {

        Pattern pattern = Pattern.compile(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        );

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "Not Found";
    }

    public List<String> extractSkills(String text) {

        List<String> skills = new ArrayList<>();

        String lowerText = text.toLowerCase();

        for (String skill : COMMON_SKILLS) {

            if (lowerText.contains(skill.toLowerCase())) {
                skills.add(skill);
            }
        }

        return skills;
    }
}

