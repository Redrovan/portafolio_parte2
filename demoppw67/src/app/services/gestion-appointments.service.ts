import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../domain/models';

@Injectable({
  providedIn: 'root',
})
export class GestionAppointments {
  private urlBase = "http://localhost:8080/portafolio/api";

  constructor(private http: HttpClient){}

  // LISTAR
  getAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.urlBase + "/appointments");
  }

  // CREAR
  create(appointment: Appointment): Observable<Appointment> {
    return this.http.post<Appointment>(this.urlBase + "/appointments", appointment);
  }

  // ACTUALIZAR
  update(appointment: Appointment): Observable<Appointment> {
    return this.http.put<Appointment>(this.urlBase + "/appointments", appointment);
  }

  // ELIMINAR
  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.urlBase + "/appointments/" + id);
  }

  // OBTENER POR ID
  getById(id: number): Observable<Appointment> {
    return this.http.get<Appointment>(this.urlBase + "/appointments/" + id);
  }
}
