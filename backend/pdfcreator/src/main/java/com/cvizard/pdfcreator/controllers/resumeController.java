package com.cvizard.pdfcreator.controllers;

import com.cvizard.pdfcreator.models.Resume;
import com.cvizard.pdfcreator.repositories.ResumeRepository;
import com.cvizard.pdfcreator.services.ResumeService;
import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class resumeController {
    private final ResumeRepository resumeRepository;
    private final ResumeService resumeService;
    @Qualifier("webApplicationContext")
    private final ResourceLoader resourceLoader;

    @GetMapping("/1")
    public Resume getResume(@RequestParam(name="key") String key) throws DocumentException, IOException {
        Resume resume = resumeRepository.findById(key).orElseThrow();
        resumeService.createPdf(key,resume);
        return resume;
    }

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> getPdfFile(@RequestParam(name= "key") String key) throws IOException {
        File file = new File("resources/"+key+".pdf");
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok().body(resource);
    }

}
