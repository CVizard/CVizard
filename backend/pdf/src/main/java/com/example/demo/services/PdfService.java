package com.example.demo.services;


import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class PdfService {
//PdfServiceż    DocumentRecognitionSettings settings = new DocumentRecognitionSettings(1);

    public void getPdfText(File file) throws IOException, TesseractException {
        
    }
}