import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { GestionAppointments } from '../../services/gestion-appointments.service';
import { UserService } from '../../services/user.service';
import { Appointment, User } from '../../domain/models';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-create-appointment',
  standalone: true,
  imports: [CommonModule, FormsModule],
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

  constructor(
    private appointmentService: GestionAppointments,
    private userService: UserService,
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {

    // 👤 Cliente logueado
    const clientId = this.auth.user?.id;
    if (clientId) {
      this.appointment.client.id = clientId;
    }

    // 📌 Obtener query param
    const pid = Number(this.route.snapshot.queryParamMap.get('programmerId'));

    // 👨‍💻 Cargar programadores
    this.userService.getProgrammers().subscribe({
      next: data => {

        this.programmers = data;

        // 🔥 Seleccionar automáticamente si viene por URL
        if (pid) {
          const selected = this.programmers.find(p => p.id === pid);

          if (selected) {
            this.appointment.programmer = selected;
          }
        }

      },
      error: err => console.error(err)
    });
  }

  // ⏰ Cargar horas disponibles
  loadAvailableHours() {

    if (!this.appointment.programmer?.id || !this.appointment.date) return;

    this.appointmentService
      .getAvailableHours(
        this.appointment.programmer.id,
        this.appointment.date
      )
      .subscribe({
        next: hours => this.availableHours = hours,
        error: err => console.error(err)
      });
  }

  // 💾 Guardar cita
  guardar() {
    this.appointmentService.create(this.appointment).subscribe({
      next: () => {
        alert('Cita agendada correctamente');
        this.router.navigate(['/appointments/my']);
      },
      error: err => console.error(err)
    });
  }
}
