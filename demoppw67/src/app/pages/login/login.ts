import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './login.html',
  styleUrls: ['./login.scss']
})
export class loginComponent {

  email = '';
  password = '';

  registerMode = false;

  newUser: any = {
    email: '',
    password: '',
    role: 'USER',
    persona: {
      nombre: '',
      cedula: '',
      direccion: ''
    }
  };

  constructor(
    private auth: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  // ================= LOGIN =================

  login() {

    if (!this.email || !this.password) {
      alert('Complete los campos');
      return;
    }

    this.auth.login(this.email, this.password).subscribe({
      next: () => this.router.navigate(['/home']),
      error: () => alert('Credenciales incorrectas')
    });

  }

  // ================= REGISTRO =================

  register() {

    if (
      !this.newUser.email ||
      !this.newUser.password ||
      !this.newUser.persona.nombre
    ) {
      alert('Complete todos los campos');
      return;
    }

    this.userService.createUser(this.newUser).subscribe({

      next: () => {

        alert('Usuario creado correctamente');

        this.registerMode = false;

        // limpiar
        this.newUser = {
          email: '',
          password: '',
          role: 'USER',
          persona: {
            nombre: '',
            cedula: '',
            direccion: ''
          }
        };

      },

      error: () => alert('Error al crear usuario')

    });

  }

}
