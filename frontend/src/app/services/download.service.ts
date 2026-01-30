import { Injectable } from '@angular/core';
import { ArtResponse } from './art.service';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  downloadImage(artwork: ArtResponse): void {
    const link = document.createElement('a');
    link.href = artwork.imageUrl;
    link.download = `${artwork.title}.png`;
    link.target = '_blank';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }
}