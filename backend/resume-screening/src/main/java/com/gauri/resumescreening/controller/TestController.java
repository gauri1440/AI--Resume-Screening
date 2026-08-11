package com.gauri.resumescreening.controller;

import com.gauri.resumescreening.model.Resume;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Resume Screening Backend is Working!";
    }

    @PostMapping("/resume")
    public Resume receiveResume(@RequestBody Resume resume) {

        System.out.println("Name: " + resume.getName());
        System.out.println("Email: " + resume.getEmail());
        System.out.println("Skills: " + resume.getSkills());
        System.out.println("Education: " + resume.getEducation());
        System.out.println("Experience: " + resume.getExperience());

        return resume;
    }
}