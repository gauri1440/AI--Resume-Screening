package com.gauri.resumescreening.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gauri.resumescreening.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}