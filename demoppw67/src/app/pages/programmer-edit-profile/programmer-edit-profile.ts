import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackButtonComponent } from '../back-button/back-button';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { User, Role } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-programmer-portfolio-edit',
  imports: [CommonModule, FormsModule, BackButtonComponent],
  templateUrl: './programmer-edit-profile.html',
  styleUrls: ['./programmer-edit-profile.scss']
})
export class ProgrammerEditProfileComponent implements OnInit {

  programmer: User = {
  email: '',
  role: Role.PROGRAMADOR,
  active: true,

  photoUrl: '',
  phone: '',
  socialLinks: '',

  persona: {
    cedula: '',
    nombre: '',
    direccion: ''
  },

  especialidad: {
    nombre: '',
    descripcion: ''
  }
};


  constructor(
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {

    const id = this.authService.user?.id;

    if (id) {
      this.userService.getById(id).subscribe({
        next: data => {

          this.programmer = {
            ...data,
            persona: data.persona || {
              cedula: '',
              nombre: '',
              direccion: ''
            },
            especialidad: data.especialidad || {
              nombre: '',
              descripcion: ''
            }
          };

        },
        error: err => console.error(err)
      });
    }
  }

  guardar() {

    this.userService.updateUser(this.programmer).subscribe({
      next: () => alert('Cambios guardados correctamente'),
      error: err => console.error(err)
    });

  }
}
