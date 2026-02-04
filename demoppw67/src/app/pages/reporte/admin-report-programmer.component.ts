import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { BackButtonComponent } from '../back-button/back-button';
import { GestionAppointments } from '../../services/gestion-appointments.service';
import { Appointment } from '../../domain/models';

import { jsPDF } from 'jspdf';
import { switchMap } from 'rxjs/operators';

@Component({
  standalone: true,
  selector: 'app-admin-report-programmer',
  imports: [CommonModule, BackButtonComponent],
  templateUrl: './admin-report-programmer.component.html',
  styleUrls: ['./admin-report-programmer.component.scss']
})
export class AdminReportProgrammerComponent implements OnInit {

  programmerId!: number;

  appointments: Appointment[] = [];

  total = 0;
  pending = 0;
  approved = 0;
  rejected = 0;

  constructor(
    private route: ActivatedRoute,
    private appointmentService: GestionAppointments
  ) {}

  ngOnInit(): void {

    // ✅ Espera cambio de ID y luego carga datos
    this.route.params
      .pipe(
        switchMap(params => {
          this.programmerId = Number(params['id']);
          return this.appointmentService.getAppointments();
        })
      )
      .subscribe({
        next: (data: any[]) => {

          this.appointments = data
            .filter(a => a.programmer?.id === this.programmerId)
            .map(a => ({
              ...a,
              status: {
                ...a.status,
                name: this.normalize(a.status.name)
              }
            }));

          this.updateCounters();

        },
        error: err => console.error(err)
      });

  }

  // =======================
  // NORMALIZAR ESTADO
  // =======================
  private normalize(status: string): 'PENDING' | 'APPROVED' | 'REJECTED' {

    if (status === 'PENDIENTE') return 'PENDING';
    if (status === 'APROBADO') return 'APPROVED';
    if (status === 'RECHAZADO') return 'REJECTED';

    return status as any;
  }

  // =======================
  // CONTADORES
  // =======================
  updateCounters() {

    this.total = this.appointments.length;

    this.pending = this.appointments.filter(
      a => a.status.name === 'PENDING'
    ).length;

    this.approved = this.appointments.filter(
      a => a.status.name === 'APPROVED'
    ).length;

    this.rejected = this.appointments.filter(
      a => a.status.name === 'REJECTED'
    ).length;

  }

  // =======================
  // PDF
  // =======================
  exportPDF() {

    const doc = new jsPDF();

    doc.text('Reporte de Asesorías del Programador', 10, 10);

    let y = 20;

    this.appointments.forEach(a => {

      doc.text(
        `${a.date} | ${a.time} | ${a.client.persona.nombre} | ${a.status.name}`,
        10,
        y
      );

      y += 8;

    });

    doc.save('reporte_asesorias.pdf');
  }

}
