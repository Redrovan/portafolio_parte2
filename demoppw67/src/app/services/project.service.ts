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

  // 🔐 PROTEGIDO
  getProjectsByUser(userId: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.api}/user/${userId}`);
  }

  // 🌍 PÚBLICO - Coincide con Java @Path("public/user/{userId}")
  getPublicProjectsByUser(userId: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.api}/public/user/${userId}`);
  }
}