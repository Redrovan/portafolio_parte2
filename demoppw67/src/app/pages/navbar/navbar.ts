import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
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

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.authService.user$.subscribe(u => {
      this.user = u;
    });

  }

  logout() {

    // cerrar sesión
    this.authService.logout();

    // redirigir al home
    this.router.navigate(['/home'], { replaceUrl: true });

  }

}
