import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface ArtGenerationRequest {
  prompt: string;
  title: string;
}

export interface ArtResponse {
  id: number;
  prompt: string;
  imageUrl: string;
  title: string;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class ArtService {
  private apiUrl = 'http://localhost:8080/api/art';

  constructor(private http: HttpClient) { }

  generateArt(request: ArtGenerationRequest): Observable<ArtResponse> {
    return this.http.post<ArtResponse>(`${this.apiUrl}/generate`, request).pipe(
      map(artwork => ({
        ...artwork,
        imageUrl: artwork.imageUrl.startsWith('/api/')
          ? `http://localhost:8080${artwork.imageUrl}`
          : artwork.imageUrl
      }))
    );
  }

  getAllArt(): Observable<ArtResponse[]> {
    return this.http.get<ArtResponse[]>(`${this.apiUrl}`).pipe(
      map(artworks => artworks.map(artwork => ({
        ...artwork,
        imageUrl: artwork.imageUrl.startsWith('/api/')
          ? `http://localhost:8080${artwork.imageUrl}`
          : artwork.imageUrl
      })))
    );
  }

  getArtById(id: number): Observable<ArtResponse> {
    return this.http.get<ArtResponse>(`${this.apiUrl}/${id}`);
  }
}
