import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { UserService } from '../../services/user.service';
import { User } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-admin-programmers',
  imports: [CommonModule, RouterModule],
  templateUrl: './admin-programmers.component.html',
  styleUrls: ['./admin-programmers.component.scss']
})
export class AdminProgrammersComponent implements OnInit {

  programmers: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.cargarProgramadores();
  }

  // =========================
  // CARGAR LISTA
  // =========================
  cargarProgramadores() {
    this.userService.getProgrammers().subscribe({
      next: data => this.programmers = data,
      error: err => console.error(err)
    });
  }

  // =========================
  // ELIMINAR
  // =========================
  eliminar(id: number) {

    if (!confirm('¿Seguro que deseas eliminar este programador?')) {
      return;
    }

    this.userService.deleteUser(id).subscribe({
      next: () => {
        alert('Programador eliminado');
        this.cargarProgramadores(); // refresca tabla
      },
      error: err => console.error(err)
    });

  }
}
