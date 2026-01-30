package com.art.gallery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    public String downloadAndSaveImage(String imageUrl) throws IOException {
        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Generate unique filename
        String fileName = UUID.randomUUID().toString() + ".png";
        Path filePath = uploadPath.resolve(fileName);
        
        // Download image from URL
        try (InputStream in = new URL(imageUrl).openStream()) {
            Files.copy(in, filePath);
        }
        
        return fileName;
    }
}