import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionAppointments } from '../../services/gestion-appointments.service';
import { AuthService } from '../../services/auth.service';
import { Appointment } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-my-appointments',
  imports: [CommonModule],
  templateUrl: './my-appointments.component.html'
})
export class MyAppointmentsComponent implements OnInit {

  list: Appointment[] = [];
  authUser: any;

  constructor(
    private appointmentService: GestionAppointments,
    private auth: AuthService
  ) {}

  ngOnInit(): void {

    this.authUser = this.auth.user;

    const id = this.authUser.id;
    const role = this.authUser.role;

    this.appointmentService.getAppointments().subscribe({

      next: data => {

        if (role === 'USER') {
          this.list = data.filter(a => a.client.id === id);
        }

        if (role === 'PROGRAMMER') {
          this.list = data.filter(a => a.programmer.id === id);
        }

      }

    });
  }

  aprobar(a: Appointment) {
    a.status = { id: 2, name: 'APPROVED' };
    this.appointmentService.update(a).subscribe();
  }

  cancelar(a: Appointment) {
    a.status = { id: 3, name: 'REJECTED' };
    this.appointmentService.update(a).subscribe();
  }

}
