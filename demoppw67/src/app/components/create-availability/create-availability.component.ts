import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { GestionAvailability } from '../../services/gestion-availability.service';
import { UserService } from '../../services/user.service';
import { Availability, User, Role } from '../../domain/models';

@Component({
  selector: 'app-create-availability',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-availability.component.html'
})
export class CreateAvailabilityComponent implements OnInit {

  availability: Availability = {
    day: '',
    startTime: '',
    endTime: '',
    programmer: {} as User
  };

  programmers: User[] = [];

  constructor(
    private availabilityService: GestionAvailability,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProgrammers();
  }

  loadProgrammers(): void {
    this.userService.getByRole(Role.PROGRAMADOR).subscribe({
      next: (data: User[]) => this.programmers = data,
      error: (err: any) => console.error(err)
    });
  }

  guardar(): void {
    this.availabilityService.guardar(this.availability).subscribe({
      next: () => {
        alert('Disponibilidad creada');
        this.router.navigate(['/availability/list']);
      },
      error: (err: any) => console.error(err)
    });
  }
}
