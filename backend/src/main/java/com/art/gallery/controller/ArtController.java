package com.art.gallery.controller;

import com.art.gallery.dto.ArtGenerationRequest;
import com.art.gallery.dto.ArtResponse;
import com.art.gallery.service.ArtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/art")

public class ArtController {
    
    private final ArtService artService;
    
    public ArtController(ArtService artService) {
        this.artService = artService;
    }
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @PostMapping("/generate")
    public ResponseEntity<ArtResponse> generateArt(@Valid @RequestBody ArtGenerationRequest request) {
        ArtResponse response = artService.generateArt(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<ArtResponse>> getAllArt() {
        List<ArtResponse> artList = artService.getAllArt();
        return ResponseEntity.ok(artList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ArtResponse> getArtById(@PathVariable Long id) {
        ArtResponse art = artService.getArtById(id);
        return ResponseEntity.ok(art);
    }
    
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}