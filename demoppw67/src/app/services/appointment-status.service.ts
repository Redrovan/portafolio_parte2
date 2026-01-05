import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface AppointmentStatus {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class AppointmentStatusService {

  private baseUrl = 'http://localhost:8080/portafolio/api/appointment-status';

  constructor(private http: HttpClient) { }

  getAll(): Observable<AppointmentStatus[]> {
    return this.http.get<AppointmentStatus[]>(this.baseUrl);
  }

  create(status: AppointmentStatus): Observable<AppointmentStatus> {
    return this.http.post<AppointmentStatus>(this.baseUrl, status);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
