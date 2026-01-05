import { Component, OnInit } from '@angular/core';
import { GestionSimpsons } from '../../services/gestion-simpsons';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-personajes-simpsons',
  imports: [CommonModule],
  templateUrl: './personajes-simpsons.html',
  styleUrl: './personajes-simpsons.scss',
})
export class PersonajesSimpsons implements OnInit {

  personajes: any[] = []

  constructor(private gs: GestionSimpsons){

  }

  ngOnInit(): void {
    
    this.gs.getPersonajes().subscribe({
      next: (resp:any) => {
        console.log("Datos recibidos:", resp);
        this.personajes = resp.results;
        console.log("Personajes:", this.personajes);
      },
      error: (err) => {
        console.error("Error en consulta: ", err);
      },
      complete: () => {
        console.log("Completado la consulta");
      }

    })

  }
}
