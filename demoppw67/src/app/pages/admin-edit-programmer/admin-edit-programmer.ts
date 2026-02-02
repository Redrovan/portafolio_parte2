import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { UserService } from '../../services/user.service';
import { EspecialidadService } from '../../services/especialidad.service';

@Component({
  standalone: true,
  selector: 'app-admin-edit-programmer',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-edit-programmer.html',
  styleUrls: ['./admin-edit-programmer.scss']
})
export class AdminEditProgrammerComponent implements OnInit {

  programmer: any = {
    persona: {},
    especialidad: {}
  };

  especialidades: any[] = [];

  id!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private especialidadService: EspecialidadService
  ) {}

  ngOnInit(): void {

    // 📌 obtener id de la URL
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    // 📌 cargar especialidades
    this.especialidadService.getAll().subscribe(data => {
      this.especialidades = data;
    });

    // 📌 cargar programador por id
    this.userService.getById(this.id).subscribe({
      next: data => {
        this.programmer = data;

        // seguridad por si vienen null
        if (!this.programmer.persona) {
          this.programmer.persona = {};
        }

        if (!this.programmer.especialidad) {
          this.programmer.especialidad = {};
        }
      },
      error: err => {
        console.error(err);
        alert('No se pudo cargar el programador');
      }
    });

  }

  guardar() {

    this.userService.update(this.programmer).subscribe({
      next: () => {
        alert('Programador actualizado');
        this.router.navigate(['/admin/programmers']);
      },
      error: err => {
        console.error(err);
        alert('Error al actualizar');
      }
    });

  }

}
