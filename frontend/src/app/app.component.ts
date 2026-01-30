import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ArtGeneratorComponent } from './components/art-generator/art-generator.component';
import { ArtGalleryComponent } from './components/art-gallery/art-gallery.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ArtGeneratorComponent, ArtGalleryComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'AI Art Gallery';
  activeTab = 'generator';

  setActiveTab(tab: string) {
    this.activeTab = tab;
  }
}
