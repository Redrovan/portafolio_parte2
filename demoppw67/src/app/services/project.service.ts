import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../domain/models';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private api = 'http://localhost:8080/portafolio/api/projects';

  constructor(private http: HttpClient) {}

  // 🔐 PRIVADO
  getProjectsByUser(userId: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.api}/user/${userId}`);
  }

  // 🌍 PUBLICO
  getPublicProjectsByUser(userId: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.api}/public/user/${userId}`);
  }

  // ➕ CREAR
  createProject(project: Project): Observable<Project> {
    return this.http.post<Project>(this.api, project);
  }

  // ✏️ ACTUALIZAR
  updateProject(project: Project): Observable<Project> {
    return this.http.put<Project>(this.api, project);
  }

  // ❌ ELIMINAR
  deleteProject(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
