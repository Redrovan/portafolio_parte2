import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ReportService {

  private api = 'http://localhost:8080/portafolio/api/appointments';

  constructor(private http: HttpClient) {}

  byStatus() {
    return this.http.get<any[]>(`${this.api}/report/status`);
  }

  byProgrammer() {
    return this.http.get<any[]>(`${this.api}/report/programmer`);
  }
}
