package com.gauri.resumescreening.controller;

import com.gauri.resumescreening.service.ResumePdfParser;
import com.gauri.resumescreening.service.ResumeTextExtractor;
import com.gauri.resumescreening.service.ScreeningUploadResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/screening")
@CrossOrigin(origins = "http://localhost:5173")
public class ScreeningController {

    private final ResumePdfParser pdfParser;
    private final ResumeTextExtractor textExtractor;

    public ScreeningController(
            ResumePdfParser pdfParser,
            ResumeTextExtractor textExtractor) {

        this.pdfParser = pdfParser;
        this.textExtractor = textExtractor;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAndScreenResume(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("requiredSkills") String requiredSkills,
            @RequestParam(value = "preferredSkills", defaultValue = "") String preferredSkills) {

        try {

            // 1. Extract PDF text
            String text = pdfParser.extractText(resume);

            // 2. Extract candidate information
            String name = textExtractor.extractName(text);
            String email = textExtractor.extractEmail(text);
            List<String> candidateSkills =
                    textExtractor.extractSkills(text);

            // 3. Prepare required skills
            List<String> required = Arrays.stream(requiredSkills.split(","))
                    .map(String::trim)
                    .filter(skill -> !skill.isEmpty())
                    .toList();

            // 4. Match required skills
            List<String> matchedSkills = new ArrayList<>();
            List<String> missingSkills = new ArrayList<>();

            for (String skill : required) {

                boolean matched = candidateSkills.stream()
                        .anyMatch(candidateSkill ->
                                candidateSkill.equalsIgnoreCase(skill)
                        );

                if (matched) {
                    matchedSkills.add(skill);
                } else {
                    missingSkills.add(skill);
                }
            }

            // 5. Calculate score
            double score = required.isEmpty()
                    ? 0
                    : ((double) matchedSkills.size()
                    / required.size()) * 100;

            score = Math.round(score * 100.0) / 100.0;

            // 6. Shortlist
            String status = score >= 70
                    ? "SHORTLISTED"
                    : "NOT SHORTLISTED";

            ScreeningUploadResponse response =
                    new ScreeningUploadResponse(
                            name,
                            email,
                            score,
                            status,
                            matchedSkills,
                            missingSkills
                    );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "error", e.getMessage()
                    ));

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body(Map.of(
                            "error", "Unable to read PDF resume"
                    ));
        }
    }
}

