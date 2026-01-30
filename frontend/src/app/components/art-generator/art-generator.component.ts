import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ArtService, ArtGenerationRequest, ArtResponse } from '../../services/art.service';
import { DownloadService } from '../../services/download.service';
import { GalleryRefreshService } from '../../services/gallery-refresh.service';

@Component({
  selector: 'app-art-generator',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './art-generator.component.html',
  styleUrl: './art-generator.component.scss'
})
export class ArtGeneratorComponent {
  prompt = '';
  title = '';
  isGenerating = false;
  generatedArt: ArtResponse | null = null;
  error = '';

  constructor(private artService: ArtService, private downloadService: DownloadService, private galleryRefreshService: GalleryRefreshService) {}

  generateArt() {
    if (!this.prompt.trim() || !this.title.trim()) {
      this.error = 'Please provide both prompt and title';
      return;
    }

    this.isGenerating = true;
    this.error = '';
    this.generatedArt = null;

    const request: ArtGenerationRequest = {
      prompt: this.prompt,
      title: this.title
    };

    this.artService.generateArt(request).subscribe({
      next: (response) => {
        this.generatedArt = response;
        this.isGenerating = false;
        this.resetForm();
        setTimeout(() => this.scrollToGeneratedArt(), 100);
      },
      error: (error) => {
        this.isGenerating = false;
        this.error = 'Failed to generate art. Please try again with a different prompt.';
        console.error('Error generating art:', error);
      }
    });
  }

  resetForm() {
    this.prompt = '';
    this.title = '';
  }

  clearGeneratedArt() {
    this.generatedArt = null;
    this.error = '';
    this.galleryRefreshService.triggerRefresh();
  }

  downloadImage(artwork: ArtResponse) {
    this.downloadService.downloadImage(artwork);
  }

  private scrollToGeneratedArt() {
    const element = document.querySelector('.generated-art');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }
}