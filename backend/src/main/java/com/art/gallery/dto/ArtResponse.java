package com.art.gallery.dto;

import java.time.LocalDateTime;

public class ArtResponse {
    private Long id;
    private String prompt;
    private String imageUrl;
    private String title;
    private LocalDateTime createdAt;
    
    public ArtResponse(Long id, String prompt, String imageUrl, String title, LocalDateTime createdAt) {
        this.id = id;
        this.prompt = prompt;
        this.imageUrl = imageUrl;
        this.title = title;
        this.createdAt = createdAt;
    }
    
    public Long getId() { return id; }
    public String getPrompt() { return prompt; }
    public String getImageUrl() { return imageUrl; }
    public String getTitle() { return title; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}