import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface NotificationMessage {
  text: string;
  type: 'success' | 'info' | 'error';
  route?: string;   // 👈 a dónde redirigir
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private messageSubject = new BehaviorSubject<NotificationMessage | null>(null);
  message$ = this.messageSubject.asObservable();

  showSuccess(text: string, route?: string) {
    this.show({ text, type: 'success', route });
  }

  showInfo(text: string, route?: string) {
    this.show({ text, type: 'info', route });
  }

  showError(text: string) {
    this.show({ text, type: 'error' });
  }

  private show(msg: NotificationMessage) {
    this.messageSubject.next(msg);
  }

  clear() {
    this.messageSubject.next(null);
  }
}
