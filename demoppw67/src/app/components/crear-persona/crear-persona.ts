import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GestionPersonas } from '../../services/gestion-personas';
import { Persona } from '../../domain/models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-persona',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './crear-persona.html'
})
export class CrearPersonaComponent {

  persona: Persona = { cedula: '', nombre: '', direccion: '' };

  constructor(
    private personaService: GestionPersonas,
    private router: Router
  ) {}

  crear() {
    this.personaService.create(this.persona).subscribe({
      next: (data) => {
        console.log("Persona creada:", data);
this.router.navigate(['/persona/listado']);
      },
      error: (err) => console.log(err)
    });
  }
}
