import { Component, OnInit } from '@angular/core';
import { GestionProductos } from '../../services/gestion-productos';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-listado',
  imports: [CommonModule, RouterModule],
  templateUrl: './listado.html',
  styleUrl: './listado.scss',
})
export class Listado implements OnInit{

  productos: any[] = []

  constructor(private gp: GestionProductos,
      private router: Router
  ){}

  ngOnInit(): void {
    this.productos = this.gp.getAll();
  }

  editar(index: number){
    console.log("Editando", this.productos[index], index);
    this.router.navigate(['/producto/editar', index]); 
  }

  eliminar(index: number){
    console.log("Eliminando", this.productos, index)
  }
  

}
