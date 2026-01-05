import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class GestionProductos {

  productos: any[] = []
  
  add(producto:any) {
    this.productos.push({ ...producto });
    console.log("productos", this.productos)
  }

  getAll() {
    return this.productos;
  }
}
