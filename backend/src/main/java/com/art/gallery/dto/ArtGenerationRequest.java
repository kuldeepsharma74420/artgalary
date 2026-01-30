package com.art.gallery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArtGenerationRequest {
    @NotBlank(message = "Prompt is required")
    @Size(max = 1000, message = "Prompt must be less than 1000 characters")
    private String prompt;
    
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;
    
    public String getPrompt() {
        return prompt;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}