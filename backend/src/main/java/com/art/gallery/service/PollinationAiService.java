package com.art.gallery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PollinationAiService {
    
    private final String apiEndpoint;
    
    public PollinationAiService(@Value("${pollination.ai.endpoint}") String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }
    
    public String generateImage(String prompt) {
        try {
            // Pollination AI generates images directly from URL with prompt parameter
            String encodedPrompt = java.net.URLEncoder.encode(prompt, "UTF-8");
            String imageUrl = apiEndpoint + "/" + encodedPrompt + "?width=1024&height=1024";
            
            return imageUrl;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate image with Pollination AI: " + e.getMessage());
        }
    }
}