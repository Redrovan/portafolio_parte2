import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Availability } from '../domain/models';

@Injectable({
  providedIn: 'root'
})
export class GestionAvailability {

  private urlBase = "http://localhost:8080/portafolio/api";

  constructor(private http: HttpClient) {}

  listar(): Observable<Availability[]> {
    return this.http.get<Availability[]>(this.urlBase + "/availability");
  }

  guardar(a: Availability): Observable<Availability> {
    return this.http.post<Availability>(this.urlBase + "/availability", a);
  }
}
