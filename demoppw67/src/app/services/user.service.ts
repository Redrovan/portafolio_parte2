import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, Role } from '../domain/models';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private api = 'http://localhost:8080/portafolio/api/users';

  constructor(private http: HttpClient) {}

  // ===============================
  // LISTAR PROGRAMADORES (PÚBLICO)
  // ===============================
  getProgrammers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.api}/programmers`);
  }

  // ===============================
  // OBTENER USUARIO POR ID
  // ===============================
  getById(id: number): Observable<User> {
    return this.http.get<User>(`${this.api}/${id}`);
  }

  // ===============================
  // ADMIN → CREAR PROGRAMADOR
  // ===============================
  createProgrammer(user: User): Observable<User> {
    return this.http.post<User>(
      `${this.api}/programmer`,
      user
    );
  }

  // ===============================
  // ACTUALIZAR PROGRAMADOR
  // ===============================
  update(user: User): Observable<User> {
    return this.http.put<User>(
      `${this.api}`,
      user
    );
  }

  // ===============================
  // ELIMINAR USUARIO
  // ===============================
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  // ===============================
  // PORTFOLIO PÚBLICO
  // ===============================
  getPublicById(id: number): Observable<User> {
    return this.http.get<User>(`${this.api}/portfolio/${id}`);
  }

  // ===============================
  // LISTAR POR ROL
  // ===============================
  getByRole(role: Role): Observable<User[]> {
    return this.http.get<User[]>(`${this.api}/role/${role}`);
  }

}
