import { Component } from '@angular/core';
import { Persona, ApiError } from '../../domain/models';
import { GestionPersonas } from '../../services/gestion-personas';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-registro-persona',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './registro-persona.html',
  styleUrl: './registro-persona.scss',
})
export class RegistroPersona {

  persona: Persona = {
    cedula: '',
    nombre: '',
    direccion: ''
  };

  mensaje = '';

  constructor(private gpService: GestionPersonas) {}

  guardar() {
    this.mensaje = '';

    this.gpService.create(this.persona).subscribe({
      next: () => {
        this.mensaje = 'Registro creado con éxito ';
        this.persona = { cedula: '', nombre: '', direccion: '' };
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          const backendError = err.error as ApiError;
          this.mensaje =
            `Error ${backendError.codigo}: ${backendError.descripcion}`;
        } else {
          this.mensaje = 'Error desconocido del servidor ';
        }
        console.error(err);
      }
    });
  }
}
