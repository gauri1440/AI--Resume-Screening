package com.gauri.resumescreening.controller;

import com.gauri.resumescreening.model.Resume;
import com.gauri.resumescreening.repository.ResumeRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeRepository resumeRepository;

    public ResumeController(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    // Save Resume
    @PostMapping
    public Resume addResume(@Valid @RequestBody Resume resume) {
        return resumeRepository.save(resume);
    }

    // Get all Resumes
    @GetMapping
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    // Get Resume by ID
    @GetMapping("/{id}")
    public Resume getResumeById(@PathVariable Long id) {
        return resumeRepository.findById(id).orElse(null);
    }

    // Update Resume
    @PutMapping("/{id}")
    public Resume updateResume(
            @PathVariable Long id,
            @Valid @RequestBody Resume newResume) {

        Resume resume = resumeRepository.findById(id).orElse(null);

        if (resume != null) {
            resume.setName(newResume.getName());
            resume.setEmail(newResume.getEmail());
            resume.setSkills(newResume.getSkills());
            resume.setEducation(newResume.getEducation());
            resume.setExperience(newResume.getExperience());

            return resumeRepository.save(resume);
        }

        return null;
    }

    // Delete Resume
    @DeleteMapping("/{id}")
    public String deleteResume(@PathVariable Long id) {

        if (resumeRepository.existsById(id)) {
            resumeRepository.deleteById(id);
            return "Resume deleted successfully";
        }

        return "Resume not found";
    }
}