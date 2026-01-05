import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GestionProductos } from '../../services/gestion-productos';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-registro-producto',
  imports: [FormsModule, RouterModule],
  templateUrl: './registro-producto.html',
  styleUrl: './registro-producto.scss',
})
export class RegistroProducto implements OnInit {

  estilo = 'yellow'

  producto = {
    codigo: 'xdr',
    nombre: '',
    stock: 0
  }

  constructor(private gp: GestionProductos,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if(id != null){
      const datos = this.gp.getAll()[Number(id)]
      this.producto = { ...datos }
    }
  }

  guardar() {
    this.gp.add(this.producto)
    console.log("intentando guardar", this.producto);
    this.router.navigate(['/producto/listado'])
  }
}
