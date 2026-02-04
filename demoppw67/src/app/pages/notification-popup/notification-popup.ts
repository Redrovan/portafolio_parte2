import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { NotificationService, NotificationMessage } from '../../services/notification.service';

@Component({
  selector: 'app-notification-popup',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div 
      class="popup"
      *ngIf="message"
      [ngClass]="message.type"
      (click)="goToNotification()">

      <strong>{{ message.text }}</strong>
      <div class="hint">Haz click para ver</div>

    </div>
  `,
  styles: [
    `
    .popup {
      position: fixed;
      top: 20px;
      right: 20px;
      padding: 14px 20px;
      border-radius: 10px;
      color: white;
      font-weight: bold;
      cursor: pointer;
      animation: fadeIn 0.3s;
      z-index: 9999;
      min-width: 220px;
    }

    .hint {
      font-size: 12px;
      margin-top: 5px;
      opacity: 0.8;
    }

    .success { background: #4caf50; }
    .info { background: #2196f3; }
    .error { background: #f44336; }

    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(-10px); }
      to { opacity: 1; transform: translateY(0); }
    }
    `
  ]
})
export class NotificationPopupComponent implements OnInit {

  message: NotificationMessage | null = null;

  constructor(
    private notificationService: NotificationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.notificationService.message$.subscribe(msg => {
      this.message = msg;
    });
  }

  goToNotification() {

    if (this.message?.route) {
      this.router.navigate([this.message.route]);
    }

    this.notificationService.clear();
  }
}
