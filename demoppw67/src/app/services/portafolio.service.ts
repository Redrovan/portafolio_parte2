import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PortfolioService {

  private api = 'http://localhost:8080/portafolio/api/users';

  constructor(private http: HttpClient) {}

  getPortfolio(id: number): Observable<any> {
    return this.http.get(`${this.api}/portfolio/${id}`);
  }
}
