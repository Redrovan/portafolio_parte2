import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Availability } from '../domain/models';

@Injectable({
  providedIn: 'root'
})
export class AvailabilityService {

  private api = 'http://localhost:8080/portafolio/api/availability';

  constructor(private http: HttpClient) {}

  listar(): Observable<Availability[]> {
    return this.http.get<Availability[]>(this.api);
  }

  guardar(a: Availability): Observable<Availability> {
    return this.http.post<Availability>(this.api, a);
  }

  actualizar(a: Availability): Observable<Availability> {
    return this.http.put<Availability>(this.api, a);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
