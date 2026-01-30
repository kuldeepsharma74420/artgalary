import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArtService, ArtResponse } from '../../services/art.service';
import { DownloadService } from '../../services/download.service';
import { GalleryRefreshService } from '../../services/gallery-refresh.service';

@Component({
  selector: 'app-art-gallery',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './art-gallery.component.html',
  styleUrl: './art-gallery.component.scss'
})
export class ArtGalleryComponent implements OnInit {
  artworks: ArtResponse[] = [];
  loading = true;
  error = '';
  selectedArtwork: ArtResponse | null = null;

  constructor(private artService: ArtService, private downloadService: DownloadService, private galleryRefreshService: GalleryRefreshService) {}

  ngOnInit() {
    this.loadArtworks();
    this.galleryRefreshService.refresh$.subscribe(() => {
      this.loadArtworks();
    });
  }

  loadArtworks() {
    this.loading = true;
    this.artService.getAllArt().subscribe({
      next: (artworks) => {
        this.artworks = artworks;
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Failed to load artworks';
        this.loading = false;
        console.error('Error loading artworks:', error);
      }
    });
  }

  refresh() {
    this.loadArtworks();
  }

  openImageModal(artwork: ArtResponse) {
    this.selectedArtwork = artwork;
  }

  closeModal() {
    this.selectedArtwork = null;
  }

  downloadImage(artwork: ArtResponse) {
    this.downloadService.downloadImage(artwork);
  }

  trackByArtwork(index: number, artwork: ArtResponse): number {
    return artwork.id;
  }
}