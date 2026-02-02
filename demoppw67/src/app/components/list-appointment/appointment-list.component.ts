import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GestionAppointments } from '../../services/gestion-appointments.service';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  standalone: true,
  imports: [CommonModule]
})
export class AppointmentListComponent implements OnInit {

  appointments: any[] = [];

  constructor(private appointmentService: GestionAppointments) { }

  ngOnInit(): void {
    this.appointmentService.getAppointments().subscribe({
      next: (data: any) => {
        this.appointments = data;
        console.log(this.appointments);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}
