package com.resumeanalyzer.service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeTextExtractor {

    public String extractText(MultipartFile file)
            throws Exception {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException(
                    "Resume file is empty."
            );
        }

        String fileName =
                file.getOriginalFilename();

        if (fileName == null) {
            fileName = "";
        }

        String lower =
                fileName.toLowerCase();

        // PDF
        if (lower.endsWith(".pdf")) {

            try (PDDocument document =
                         Loader.loadPDF(file.getBytes())) {

                PDFTextStripper stripper =
                        new PDFTextStripper();

                return stripper.getText(document);
            }
        }

        // DOCX
        if (lower.endsWith(".docx")) {

            try (InputStream input =
                         file.getInputStream();

                 XWPFDocument document =
                         new XWPFDocument(input);

                 XWPFWordExtractor extractor =
                         new XWPFWordExtractor(document)) {

                return extractor.getText();
            }
        }

        // TXT
        if (lower.endsWith(".txt")) {

            return new String(
                    file.getBytes(),
                    StandardCharsets.UTF_8
            );
        }

        throw new IllegalArgumentException(
                "Only PDF, DOCX and TXT files are supported."
        );
    }
}