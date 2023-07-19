package com.cvizard.pdfcreator.controllers;

import com.cvizard.pdfcreator.models.Resume;
import com.cvizard.pdfcreator.repositories.ResumeRepository;
import com.cvizard.pdfcreator.services.ResumeService;
import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/creator")
@AllArgsConstructor
public class ResumeController {
    private final ResumeRepository resumeRepository;
    private final ResumeService resumeService;

    @GetMapping("/templates")
    public void getResumeTemplates() {
        //TODO return list of templates with logo
    }

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> getPdfFile(@RequestParam(name = "key") String key) throws IOException, DocumentException {
        Resume resume = resumeRepository.findById(key).orElseThrow();
        resumeService.createPdf(key, resume);
        File file = new File("resources/" + key + ".pdf");
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/test")
    public HashMap<String, String> testGet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "test");
        return map;
    }

}
