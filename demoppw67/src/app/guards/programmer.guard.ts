import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProgrammerGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const stored = localStorage.getItem('user');

    if (!stored) {
      this.router.navigate(['/login']);
      return false;
    }

    const user = JSON.parse(stored);

    // CAMBIO AQUÍ: Debe ser 'PROGRAMMER' para que coincida con tu Local Storage
    if (user.role === 'PROGRAMMER') { 
      return true;
    }

    console.log('Acceso denegado. Rol encontrado:', user.role); // Para debug
    this.router.navigate(['/home']);
    return false;
  }
}