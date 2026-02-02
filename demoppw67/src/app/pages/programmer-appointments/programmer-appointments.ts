import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionAppointments } from '../../services/gestion-appointments.service';
import { AuthService } from '../../services/auth.service';
import { Appointment } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-programmer-appointments',
  imports: [CommonModule],
  templateUrl: './programmer-appointments.html'
})
export class ProgrammerAppointmentsComponent implements OnInit {

  list: Appointment[] = [];

  constructor(
    private appointmentService: GestionAppointments,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    const id = this.auth.user!.id;

    this.appointmentService.getAppointments().subscribe({
      next: data => {
        this.list = data.filter(a => a.programmer.id === id);
      }
    });
  }

  aprobar(a: Appointment) {
    a.status = { id: 2, name: 'APPROVED' };

    this.appointmentService.update(a).subscribe(() => this.load());
  }

  rechazar(a: Appointment) {
    a.status = { id: 3, name: 'REJECTED' };

    this.appointmentService.update(a).subscribe(() => this.load());
  }
}
