package com.cvizard.pdfcreator.controllers;

import com.cvizard.pdfcreator.models.Resume;
import com.cvizard.pdfcreator.repositories.ResumeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class resumeController {
    private final ResumeRepository resumeRepository;
    @GetMapping
    public Optional<Resume> getResume(@RequestParam(name="key") String key){
        return resumeRepository.findById(key);
    }
}
