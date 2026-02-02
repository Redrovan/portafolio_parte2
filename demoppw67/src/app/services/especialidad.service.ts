import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Especialidad {
  id: number;
  nombre: string;
}

@Injectable({
  providedIn: 'root'
})
export class EspecialidadService {

  private api = 'http://localhost:8080/portafolio/api/especialidades';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Especialidad[]> {
    return this.http.get<Especialidad[]>(this.api);
  }
}
