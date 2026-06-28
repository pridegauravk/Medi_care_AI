package com.example.ai.demo.controller;


import com.example.ai.demo.service.DocumentService;
import com.example.ai.demo.service.AIService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final DocumentService documentService;
    private final AIService aiService;

    public FileUploadController(DocumentService documentService,
                                AIService aiService) {
        this.documentService = documentService;
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // Step 1: Extract text
        String text = documentService.extractText(file);

        // Step 2: Send to AI
        String result = aiService.analyzeMedicalReport(text);

        return ResponseEntity.ok(result);
    }
}