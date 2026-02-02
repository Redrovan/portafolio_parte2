import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const userGuard = () => {
  const router = inject(Router);
  const user = JSON.parse(localStorage.getItem('user') || 'null');

  if (user && user.role === 'USER') return true;

  router.navigate(['/']);
  return false;
};
