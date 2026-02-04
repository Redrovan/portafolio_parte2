import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AvailabilityService } from '../../services/availability.service';
import { Availability } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-list-availability',
  imports: [CommonModule],
  templateUrl: './list-availability.component.html'
})
export class ListAvailabilityComponent implements OnInit {

  list: Availability[] = [];

  constructor(private service: AvailabilityService) {}

  ngOnInit(): void {
    this.service.listar().subscribe({
      next: (data: Availability[]) => this.list = data,
      error: err => console.error(err)
    });
  }
}
