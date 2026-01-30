package com.art.gallery.service;

import com.art.gallery.dto.ArtGenerationRequest;
import com.art.gallery.dto.ArtResponse;
import com.art.gallery.entity.Art;
import com.art.gallery.repository.ArtRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtService {
    
    private final PollinationAiService pollinationAiService;
    private final ArtRepository artRepository;
    private final FileStorageService fileStorageService;
    
    public ArtService(PollinationAiService pollinationAiService, ArtRepository artRepository, FileStorageService fileStorageService) {
        this.pollinationAiService = pollinationAiService;
        this.artRepository = artRepository;
        this.fileStorageService = fileStorageService;
    }
    
    public ArtResponse generateArt(ArtGenerationRequest request) {
        try {
            String imageUrl = pollinationAiService.generateImage(request.getPrompt());
            
            // Download and save image locally
            String fileName = fileStorageService.downloadAndSaveImage(imageUrl);
            
            Art art = new Art();
            art.setPrompt(request.getPrompt());
            art.setTitle(request.getTitle());
            art.setImageFileName(fileName);
            
            Art savedArt = artRepository.save(art);
            
            return new ArtResponse(
                savedArt.getId(),
                savedArt.getPrompt(),
                "/api/art/image/" + savedArt.getImageFileName(),
                savedArt.getTitle(),
                savedArt.getCreatedAt()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate image: " + e.getMessage());
        }
    }
    
    public List<ArtResponse> getAllArt() {
        return artRepository.findAllByOrderByCreatedAtDesc()
            .stream()
            .map(art -> new ArtResponse(
                art.getId(),
                art.getPrompt(),
                "/api/art/image/" + art.getImageFileName(),
                art.getTitle(),
                art.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }
    
    public ArtResponse getArtById(Long id) {
        Art art = artRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Art not found with id: " + id));
        
        return new ArtResponse(
            art.getId(),
            art.getPrompt(),
            "/api/art/image/" + art.getImageFileName(),
            art.getTitle(),
            art.getCreatedAt()
        );
    }
}