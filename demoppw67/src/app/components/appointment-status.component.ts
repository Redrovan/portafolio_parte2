import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';   
import { FormsModule } from '@angular/forms';   
import { AppointmentStatusService } from '../services/appointment-status.service';
import { AppointmentStatus } from '../domain/models';

@Component({
  selector: 'app-appointment-status',
  templateUrl: './appointment-status.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule],
  //styleUrls: ['./appointment-status.component.css']
})
export class AppointmentStatusComponent implements OnInit {

  list: AppointmentStatus[] = [];
  name: string = '';

  constructor(private service: AppointmentStatusService) { }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.service.getAll().subscribe(
      data => this.list = data,
      err => console.log(err)
    );
  }

  save() {
    if (this.name.trim().length === 0) return;

    const newStatus: AppointmentStatus = { id: 0, name: this.name };

    this.service.create(newStatus).subscribe(
      () => {
        this.name = '';
        this.load();
      },
      err => console.log(err)
    );
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      () => this.load(),
      err => console.log(err)
    );
  }
}
