package com.gauri.resumescreening.controller;

import com.gauri.resumescreening.model.JobRequirement;
import com.gauri.resumescreening.model.Resume;
import com.gauri.resumescreening.model.ScreeningRequest;
import com.gauri.resumescreening.model.ScreeningResult;
import com.gauri.resumescreening.repository.ResumeRepository;
import com.gauri.resumescreening.service.ResumeScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/screening")
public class ScreeningController {

    private final ResumeScreeningService screeningService;
    private final ResumeRepository resumeRepository;

    public ScreeningController(
            ResumeScreeningService screeningService,
            ResumeRepository resumeRepository) {

        this.screeningService = screeningService;
        this.resumeRepository = resumeRepository;
    }

    // Screen Resume directly from request
    @PostMapping
    public ScreeningResult screenResume(
            @RequestBody ScreeningRequest request) {

        return screeningService.screenResume(
                request.getResume(),
                request.getJobRequirement()
        );
    }

    // Screen existing Resume from database
    @PostMapping("/{resumeId}")
    public ScreeningResult screenExistingResume(
            @PathVariable Long resumeId,
            @RequestBody JobRequirement jobRequirement) {

        Resume resume = resumeRepository
                .findById(resumeId)
                .orElse(null);

        if (resume == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Resume not found with id: " + resumeId
            );
        }

        return screeningService.screenResume(
                resume,
                jobRequirement
        );
    }
}