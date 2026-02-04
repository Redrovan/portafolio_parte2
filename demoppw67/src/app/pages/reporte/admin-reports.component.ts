import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportService } from '../../services/report.service';

@Component({
  standalone: true,
  imports: [CommonModule],
  template: `
  <h2>📊 Reportes Administrativos</h2>

  <h3>Asesorías por estado</h3>
  <div *ngFor="let r of statusReport">
    {{ r.status }} → {{ r.total }}
  </div>

  <h3>Asesorías por programador</h3>
  <div *ngFor="let r of programmerReport">
    {{ r.programmer }} → {{ r.total }}
  </div>
  `
})
export class AdminReportsComponent implements OnInit {

  statusReport:any[]=[];
  programmerReport:any[]=[];

  constructor(private report: ReportService) {}

  ngOnInit() {

    this.report.byStatus().subscribe(d=>this.statusReport=d);
    this.report.byProgrammer().subscribe(d=>this.programmerReport=d);

  }
}
