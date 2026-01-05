import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Persona } from '../domain/models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GestionPersonas {
  private urlBase = "http://localhost:8080/portafolio/api";

  constructor(private http: HttpClient){}

  // GET personas
  getPersonas(): Observable<Persona[]> {
    const url = this.urlBase + "/persona";
    return this.http.get<Persona[]>(url);
  }

  // GET persona por cedula
  getPersona(cedula: string): Observable<Persona> {
    const url = this.urlBase + "/persona/" + cedula;
    return this.http.get<Persona>(url);
  }

  // CREATE persona
  create(persona: Persona): Observable<Persona> {
    const url = this.urlBase + "/persona";
    return this.http.post<Persona>(url, persona);
  }

  // UPDATE persona
  update(persona: Persona): Observable<Persona> {
    const url = this.urlBase + "/persona";
    return this.http.put<Persona>(url, persona);
  }

  // DELETE persona
  delete(cedula: string): Observable<void> {
    const url = this.urlBase + "/persona/" + cedula;
    return this.http.delete<void>(url);
  }
}
