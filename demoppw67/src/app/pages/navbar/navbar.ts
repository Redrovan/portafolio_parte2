import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserWithToken } from '../../domain/user-with-token.model';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.scss']
})
export class NavbarComponent implements OnInit {
  user: UserWithToken | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    // Nos suscribimos al observable del servicio para detectar cambios de sesión
    this.authService.user$.subscribe(u => {
      this.user = u;
    });
  }

  logout() {
    this.authService.logout();
  }
}