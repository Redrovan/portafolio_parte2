import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { User } from '../../domain/models';

@Component({
  selector: 'app-programmer-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './programmer-dashboard.html',
  styleUrls: ['./programmer-dashboard.scss']
})
export class ProgrammerDashboardComponent implements OnInit {

  profile?: User;

  constructor(
    private userService: UserService,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    const id = this.auth.user?.id;
    if (!id) return;

    this.userService.getById(id).subscribe({
      next: (data: User) => this.profile = data,
      error: err => console.error(err)
    });
  }
}
