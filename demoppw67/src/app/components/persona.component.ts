import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GestionPersonas } from '../services/gestion-personas';
import { Persona } from '../domain/models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class PersonaComponent implements OnInit {

  personas: Persona[] = [];
  cargando: boolean = false;
  mensaje: string = '';

  constructor(
    private personaService: GestionPersonas,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.cargando = true;
    this.personaService.getPersonas().subscribe({
      next: (data: Persona[]) => {
        this.personas = data;
        this.cargando = false;

        if (this.personas.length === 0) {
          this.mensaje = "No hay personas registradas.";
        } else {
          this.mensaje = "";
        }
      },
      error: (err) => {
        console.log(err);
        this.cargando = false;
        this.mensaje = "Error al cargar personas.";
      }
    });
  }

  eliminar(cedula: string) {
    this.personaService.delete(cedula).subscribe({
      next: () => {
        this.personas = this.personas.filter(p => p.cedula !== cedula);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  editar(cedula: string) {
    this.router.navigate(['/persona/editar', cedula]);
  }
}
