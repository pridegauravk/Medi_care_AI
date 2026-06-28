package com.example.ai.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String analyzeMedicalReport(String reportText) {

        String prompt = """
                You are a health assistant AI.

                Analyze the following medical report and provide:
                1. Health summary
                2. Diet recommendations
                3. Exercise suggestions
                4. Any risks

                Medical Report:
                """ + reportText;

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
