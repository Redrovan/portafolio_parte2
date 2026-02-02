import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { User } from '../../domain/models';
import { UserWithToken } from '../../domain/user-with-token.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.scss']
})
export class HomeComponent implements OnInit {

  programmers: User[] = [];
  authUser: UserWithToken | null = null;

  constructor(
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {

    this.authUser = this.authService.user;

    this.loadProgrammers();
  }

  loadProgrammers() {
    this.userService.getProgrammers().subscribe({
      next: data => this.programmers = data,
      error: err => console.error(err)
    });
  }
}
