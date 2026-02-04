import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Role } from '../../domain/models';

import { AvailabilityService } from '../../services/availability.service';
import { AuthService } from '../../services/auth.service';
import { Availability } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-create-availability',
  imports: [CommonModule, FormsModule],
  templateUrl: './create-availability.component.html'
})
export class CreateAvailabilityComponent {

  availability: Availability = {
    day: '',
    startTime: '',
    endTime: '',
    mode: 'ONLINE',
    programmer: {
      id: 0,
      email: '',
      role:Role.PROGRAMADOR,
      persona: { nombre: '', direccion: '', cedula: '' },
      especialidad: { nombre: '', descripcion: '' },
      active: true
    }
  };

  constructor(
    private availabilityService: AvailabilityService,
    private auth: AuthService
  ) {}

  guardar() {

    const id = this.auth.user?.id;
    if (!id) return;

    this.availability.programmer.id = id;

    this.availabilityService.guardar(this.availability).subscribe({
      next: () => alert('Disponibilidad guardada'),
      error: err => console.error(err)
    });
  }
}
