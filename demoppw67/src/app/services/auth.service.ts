import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { UserWithToken } from '../domain/user-with-token.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/portafolio/api';

  private userSubject = new BehaviorSubject<UserWithToken | null>(null);
  user$ = this.userSubject.asObservable();

  constructor(private http: HttpClient) {

    const stored = localStorage.getItem('user');

    if (stored) {
      try {
        this.userSubject.next(JSON.parse(stored));
      } catch {
        this.userSubject.next(null);
      }
    }

  }

  // =======================
  // LOGIN
  // =======================
  login(email: string, password: string): Observable<UserWithToken> {

    return this.http
      .post<UserWithToken>(`${this.baseUrl}/auth/login`, { email, password })
      .pipe(
        tap(user => {

          localStorage.setItem('token', user.token);
          localStorage.setItem('user', JSON.stringify(user));

          this.userSubject.next(user);

        })
      );

  }

  // =======================
  // LOGOUT (FIX REAL)
  // =======================
  logout(): void {

    localStorage.removeItem('token');
    localStorage.removeItem('user');

    this.userSubject.next(null);

  }

  // =======================
  // HELPERS
  // =======================
  get user(): UserWithToken | null {
    return this.userSubject.value;
  }

  isLoggedIn(): boolean {
    return !!this.user;
  }

  getToken(): string | null {
    return this.user?.token ?? null;
  }

}
