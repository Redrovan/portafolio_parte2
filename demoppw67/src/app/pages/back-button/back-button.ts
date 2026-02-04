import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Location } from '@angular/common';

@Component({
  selector: 'app-back-button',
  standalone: true,
  imports: [CommonModule],
  template: `
    <button class="back-btn" (click)="goBack()">
      ⬅ Regresar
    </button>
  `,
  styles: [`
    .back-btn {
      background: #1e88e5;
      color: white;
      border: none;
      padding: 8px 14px;
      border-radius: 8px;
      cursor: pointer;
      margin-bottom: 15px;
    }

    .back-btn:hover {
      background: #1565c0;
    }
  `]
})
export class BackButtonComponent {

  constructor(private location: Location) {}

  goBack() {
    this.location.back();
  }

}
