package com.gauri.resumescreening.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String skills;
    private String education;
    private int experience;

    // Default Constructor
    public Resume() {
    }

    // Parameterized Constructor
    public Resume(String name, String email, String skills,
                  String education, int experience) {
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    public Long getId() {
    return id;
}

    public void setId(Long id) {
    this.id = id;
    }
}