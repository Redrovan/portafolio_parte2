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
  // PROGRAMADORES (PÚBLICO)
  // ===============================
  getProgrammers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.api}/programmers`);
  }

  // ===============================
  // OBTENER POR ID
  // ===============================
  getById(id: number): Observable<User> {
    return this.http.get<User>(`${this.api}/${id}`);
  }

  // ===============================
  // ADMIN → CREAR PROGRAMADOR
  // ===============================
  createProgrammer(user: User): Observable<User> {
    return this.http.post<User>(`${this.api}/programmer`, user);
  }

  // ===============================
  // ACTUALIZAR PERFIL ✅ (CON ID)
  // ===============================
  updateUser(user: User): Observable<User> {

    if (!user.id) {
      throw new Error('User ID requerido para actualizar');
    }

    return this.http.put<User>(
      `${this.api}/${user.id}`,   // 🔥 AQUÍ ESTABA EL PROBLEMA
      user
    );
  }

  // ===============================
  // ELIMINAR
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

  // ===============================
// CREAR USUARIO NORMAL
// ===============================
createUser(user: any): Observable<User> {
  return this.http.post<User>(this.api, user);
}

}
