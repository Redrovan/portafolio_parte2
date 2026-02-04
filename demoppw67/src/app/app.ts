import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar';
import { NotificationPopupComponent } from './pages/notification-popup/notification-popup';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent,NotificationPopupComponent], // <-- CORREGIDO
  templateUrl: './app.html'
})
export class App {}
