import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NotificationFastapiService, Notification } from '../../services/notification-fastapi.service';

@Component({
  standalone: true,
  imports: [FormsModule],
  selector: 'app-test-notifications',
  templateUrl: './test-notifications.html'
})
export class TestNotificationsComponent {

  phone = '';
  message = '';
  time = '';

  constructor(private notificationService: NotificationFastapiService) {}

  send() {

    const notification: Notification = {
      phone: this.phone,
      message: this.message,
      scheduled_time: this.time
    };

    this.notificationService.create(notification).subscribe({
      next: res => {
        alert('Notificación creada!');
        console.log(res);
      },
      error: err => console.error(err)
    });

  }

  process() {

    this.notificationService.process().subscribe({
      next: res => {
        alert('Notificaciones procesadas!');
        console.log(res);
      },
      error: err => console.error(err)
    });

  }

}
