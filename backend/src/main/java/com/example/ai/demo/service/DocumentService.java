package com.example.ai.demo.service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {

    public String extractText(MultipartFile file) {
        try {
            Tika tika = new Tika();
            return tika.parseToString(file.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException("Error reading document", e);
        }
    }
}