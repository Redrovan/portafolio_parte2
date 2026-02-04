import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BackButtonComponent } from '../back-button/back-button';
import { GestionAppointments } from '../../services/gestion-appointments.service';
import { UserService } from '../../services/user.service';
import { Appointment, User } from '../../domain/models';
import { AuthService } from '../../services/auth.service';
import { NotificationService } from '../../services/notification.service';

@Component({
  standalone: true,
  selector: 'app-create-appointment',
  imports: [CommonModule, FormsModule, BackButtonComponent],
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.scss']
})
export class CreateAppointmentComponent implements OnInit {

  appointment: Appointment = {
    client: {} as User,
    programmer: {} as User,
    date: '',
    time: '',
    status: { id: 1, name: 'PENDING' },
    comment: '',
    mode: 'Virtual'
  };

  programmers: User[] = [];
  availableHours: string[] = [];
  noSchedule = false;

  constructor(
    private appointmentService: GestionAppointments,
    private userService: UserService,
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private notification: NotificationService   // ✅ popup service
  ) {}

  ngOnInit(): void {

    const clientId = this.auth.user?.id;
    if (clientId) this.appointment.client.id = clientId;

    const pid = Number(this.route.snapshot.queryParamMap.get('programmerId'));

    this.userService.getProgrammers().subscribe({
      next: data => {

        this.programmers = data;

        if (pid) {
          const selected = this.programmers.find(p => p.id === pid);
          if (selected) this.appointment.programmer = selected;
        }
      },
      error: err => console.error(err)
    });
  }

  // =======================
  // HORAS DISPONIBLES
  // =======================
  loadAvailableHours() {

    if (!this.appointment.programmer?.id || !this.appointment.date) return;

    this.appointmentService
      .getAvailableHours(
        this.appointment.programmer.id,
        this.appointment.date
      )
      .subscribe({
        next: hours => {

          this.availableHours = hours;
          this.noSchedule = hours.length === 0;

          if (this.noSchedule) {
            this.notification.showInfo('No hay horarios disponibles para ese día');
          }

        },
        error: err => console.error(err)
      });
  }

  // =======================
  // GUARDAR CITA
  // =======================
  guardar() {

    this.appointmentService.create(this.appointment).subscribe({

      next: () => {

        // ✅ NOTIFICACIÓN USUARIO
        this.notification.showSuccess('Asesoría agendada correctamente');

        // ✅ NOTIFICACIÓN PROGRAMADOR (simulada en frontend)
        this.notification.showInfo('El programador recibió una nueva asesoría');

        // redirigir
        setTimeout(() => {
          this.router.navigate(['/appointments/my']);
        }, 1000);

      },

      error: err => {

        this.notification.showError(
          err.error || 'Horario ya ocupado'
        );

      }

    });
  }

}
