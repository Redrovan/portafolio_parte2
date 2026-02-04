import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackButtonComponent } from '../back-button/back-button';
import { AvailabilityService } from '../../services/availability.service';
import { AuthService } from '../../services/auth.service';
import { Availability, Role } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-programmer-availability',
  imports: [CommonModule, FormsModule, BackButtonComponent ],
  templateUrl: './programmer-availability.html',
  styleUrls: ['./programmer-availability.scss']
})
export class ProgrammerAvailabilityComponent implements OnInit {

  list: Availability[] = [];

  editMode = false;
  editingId?: number;

  newAvailability: Availability = this.emptyAvailability();

  constructor(
    private service: AvailabilityService,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    this.cargar();
  }

  // =====================
  // CARGAR
  // =====================

  cargar() {
    this.service.listar().subscribe({
      next: data => this.list = data,
      error: err => console.error(err)
    });
  }

  // =====================
  // GUARDAR / ACTUALIZAR
  // =====================

  guardar() {

    const userId = this.auth.user?.id;
    if (!userId) return;

    this.newAvailability.programmer.id = userId;

    // ✅ VALIDACIONES

    if (!this.newAvailability.day ||
        !this.newAvailability.startTime ||
        !this.newAvailability.endTime) {

      alert('Completa todos los campos');
      return;
    }

    if (this.newAvailability.endTime <= this.newAvailability.startTime) {
      alert('Hora fin debe ser mayor a hora inicio');
      return;
    }

    // 🚫 NO REPETIR DÍA

    const repeated = this.list.find(a =>
      a.day === this.newAvailability.day &&
      a.id !== this.editingId
    );

    if (repeated) {
      alert('Ya tienes un horario para ese día');
      return;
    }

    // =====================

    if (this.editMode) {

      this.newAvailability.id = this.editingId;

      this.service.actualizar(this.newAvailability).subscribe({
        next: () => {
          this.reset();
          this.cargar();
        }
      });

    } else {

      this.service.guardar(this.newAvailability).subscribe({
        next: () => {
          this.reset();
          this.cargar();
        }
      });
    }
  }

  // =====================
  // EDITAR
  // =====================

  editar(a: Availability) {

    this.editMode = true;
    this.editingId = a.id;

    this.newAvailability = JSON.parse(JSON.stringify(a));
  }

  // =====================
  // ELIMINAR
  // =====================

  eliminar(id?: number) {

    if (!id) return;

    if (!confirm('¿Eliminar horario?')) return;

    this.service.eliminar(id).subscribe({
      next: () => this.cargar()
    });
  }

  // =====================
  // RESET
  // =====================

  reset() {
    this.editMode = false;
    this.editingId = undefined;
    this.newAvailability = this.emptyAvailability();
  }

  emptyAvailability(): Availability {
    return {
      day: '',
      startTime: '',
      endTime: '',
      mode: 'ONLINE',
      programmer: {
        id: 0,
        email: '',
        role: Role.PROGRAMADOR,
        persona: { nombre: '', direccion: '', cedula: '' },
        especialidad: { nombre: '', descripcion: '' },
        active: true
      }
    };
  }
}
