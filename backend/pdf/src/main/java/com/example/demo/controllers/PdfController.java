package com.example.demo.controllers;

import com.example.demo.services.PdfService;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PdfController {
    private final PdfService pdfService;
    @PostMapping
    @ResponseStatus(OK)
    public String pdfUpload(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        pdfService.getPdfText(convFile);
        return "File uploaded successfully";
    }
}
