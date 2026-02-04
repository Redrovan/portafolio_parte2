import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { GestionAppointments } from '../../services/gestion-appointments.service';
import { NotificationService } from '../../services/notification.service';

import { User } from '../../domain/models';
import { UserWithToken } from '../../domain/user-with-token.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.scss']
})
export class HomeComponent implements OnInit {

  programmers: User[] = [];
  authUser: UserWithToken | null = null;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private appointmentService: GestionAppointments,
    private notification: NotificationService
  ) {}

  ngOnInit(): void {

    // 🔔 Escuchar cambios de sesión
    this.authService.user$.subscribe(user => {

      this.authUser = user;

      if (!user) return;

      // 👨‍💻 PROGRAMADOR
      if (user.role === 'PROGRAMMER') {
        this.checkProgrammerNotifications(user.id!);
      }

      // 👤 USUARIO
      if (user.role === 'USER') {
        this.checkUserNotifications(user.id!);
      }

    });

    this.loadProgrammers();
  }

  // =======================
  // PROGRAMADOR → NUEVAS ASESORÍAS
  // =======================
  checkProgrammerNotifications(programmerId: number) {

    this.appointmentService.getAppointments().subscribe(data => {

      const nuevas = data.filter(a =>
        a.programmer?.id === programmerId &&
        a.status.name === 'PENDING'
      );

      if (nuevas.length > 0) {

        this.notification.showInfo(
          `Tienes ${nuevas.length} nueva(s) asesoría(s) pendiente(s)`,
          '/appointments/my'   // 👉 va a solicitudes
        );

      }

    });

  }

  // =======================
  // USUARIO → ASESORÍAS ACEPTADAS
  // =======================
  checkUserNotifications(userId: number) {

    this.appointmentService.getAppointments().subscribe(data => {

      const aprobadas = data.filter(a =>
        a.client?.id === userId &&
        a.status.name === 'APPROVED'
      );

      if (aprobadas.length > 0) {

        // 👉 Tomamos el nombre del programador que aceptó
        const programador = aprobadas[0].programmer?.persona?.nombre || 'El programador';

        this.notification.showSuccess(
          `${programador} aceptó tu solicitud de asesoría`,
          '/appointments/my'   // 👉 va a mis asesorías
        );

      }

    });

  }

  // =======================
  // CARGAR PROGRAMADORES
  // =======================
  loadProgrammers() {

    this.userService.getProgrammers().subscribe({
      next: data => this.programmers = data,
      error: err => console.error(err)
    });

  }

}
