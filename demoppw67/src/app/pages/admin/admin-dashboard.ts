import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../../domain/models';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard.html'
})
export class AdminDashboardComponent implements OnInit {

  programmers: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.userService.getProgrammers().subscribe({
      next: data => this.programmers = data,
      error: err => console.error(err)
    });
  }

  delete(id: number) {
    this.userService.deleteUser(id).subscribe({
      next: () => this.load(),
      error: err => console.error(err)
    });
  }
}
