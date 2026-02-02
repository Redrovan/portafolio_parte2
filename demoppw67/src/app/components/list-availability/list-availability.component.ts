import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionAvailability } from '../../services/gestion-availability.service';
import { Availability } from '../../domain/models';

@Component({
  selector: 'app-list-availability',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './list-availability.component.html'
})
export class ListAvailabilityComponent implements OnInit {

  list: Availability[] = [];

  constructor(private service: GestionAvailability) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.service.listar().subscribe({
      next: (data) => this.list = data,
      error: (err) => console.error(err)
    });
  }
}
