import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GestionPersonas } from '../../services/gestion-personas';
import { Persona } from '../../domain/models';

@Component({
  selector: 'app-editar-persona',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './editar-persona.html'
})
export class EditarPersona implements OnInit {

  persona: Persona = { cedula: '', nombre: '', direccion: '' };

  constructor(
    private route: ActivatedRoute,
    private personaService: GestionPersonas,
    private router: Router
  ) {}

  ngOnInit(): void {
    const cedula = this.route.snapshot.params['cedula'];
    this.personaService.getPersona(cedula).subscribe({
      next: (data) => this.persona = data,
      error: (err) => console.log(err)
    });
  }

  actualizar() {
    this.personaService.update(this.persona).subscribe({
      next: () => {
        console.log("Persona actualizada");
        this.router.navigate(['/persona/listado']);
      },
      error: (err) => console.log(err)
    });
  }
}
