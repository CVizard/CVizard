package com.cvizard.pdfcreator.services;

import com.cvizard.pdfcreator.models.Resume;
import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.io.FileOutputStream;

@Service
@AllArgsConstructor
public class ResumeService {
    private final SpringTemplateEngine templateEngine;

    public void createPdf(String key, Resume resume) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();

        Context context = new Context();
        context.setVariable("resume",resume);
        String processed = templateEngine.process("resume", context);
        renderer.setDocumentFromString(processed);
        renderer.layout();

        try (FileOutputStream fos = new FileOutputStream("resources/"+key+".pdf")) {
            renderer.createPDF(fos);
        }
    }
}
