package com.art.gallery.repository;

import com.art.gallery.entity.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    List<Art> findAllByOrderByCreatedAtDesc();
}