import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Notification {
  phone: string;
  message: string;
  scheduled_time: string;
}

@Injectable({
  providedIn: 'root'
})
export class NotificationFastapiService {

  private baseUrl = 'http://localhost:8000/api/notifications';

  constructor(private http: HttpClient) {}

  create(notification: Notification): Observable<any> {
    return this.http.post(this.baseUrl, notification);
  }

  process(): Observable<any> {
    return this.http.post(`${this.baseUrl}/process`, {});
  }
}
