package com.art.gallery.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "arts")
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 1000)
    private String prompt;
    
    @Column(nullable = false)
    private String imageFileName;
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public Art() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
    
    public String getImageFileName() { return imageFileName; }
    public void setImageFileName(String imageFileName) { this.imageFileName = imageFileName; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}