import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from '../../services/user.service';
import { EspecialidadService, Especialidad } from '../../services/especialidad.service';

@Component({
  standalone: true,
  selector: 'app-admin-create-programmer',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-create-programmer.html',
  styleUrls: ['./admin-create-programmer.scss']
})
export class AdminCreateProgrammerComponent implements OnInit {

  especialidades: Especialidad[] = [];

  programmer: any = {
    email: '',
    password: '',
    role: 'PROGRAMMER',   // lo mandamos como string (backend lo convierte)
    active: true,
    photoUrl: '',
    phone: '',
    socialLinks: '',
    persona: {
      nombre: '',
      direccion: ''
    },
    especialidad: {
      id: null
    }
  };

  constructor(
    private userService: UserService,
    private especialidadService: EspecialidadService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.especialidadService.getAll().subscribe({
      next: data => this.especialidades = data,
      error: err => console.error(err)
    });
  }

  guardar() {
    this.userService.createProgrammer(this.programmer).subscribe({
      next: () => {
        alert('Programador creado correctamente');
        this.router.navigate(['/admin/programmers']);
      },
      error: err => {
        console.error(err);
        alert('Error al crear programador');
      }
    });
  }
}
