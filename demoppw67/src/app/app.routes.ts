import { Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home';
import { PersonaComponent } from './components/list-persona/persona.component';
import { AppointmentListComponent } from './components/list-appointment/appointment-list.component';
import { AppointmentStatusComponent } from './components/list-appointment-status/appointment-status.component';
import { CrearPersonaComponent } from './components/crear-persona/crear-persona';
import { EditarPersona } from './components/editar-persona/editar-persona';
import { CreateAvailabilityComponent } from './components/create-availability/create-availability.component';
import { ListAvailabilityComponent } from './components/list-availability/list-availability.component';

import { AdminDashboardComponent } from './pages/admin/admin-dashboard';
import { ProgrammerDashboardComponent } from './pages/programmer/programmer-dashboard';

import { AdminGuard } from './guards/admin.guard';
import { ProgrammerGuard } from './guards/programmer.guard';

import { loginComponent } from './pages/login/login';

import { ProgrammerPortfolioComponent } from './pages/portfolio-programmer/programmer-portfolio';

import { CreateAppointmentComponent } from './pages/create-appointment/create-appointment.component';
import { ProgrammerAppointmentsComponent } from './pages/programmer-appointments/programmer-appointments';
import { MyAppointmentsComponent } from './pages/my-appointments/my-appointments.component';

import { AdminProgrammersComponent } from './pages/admin-programmers/admin-programmers.component';
import { AdminCreateProgrammerComponent } from './pages/admin-create-programmer/admin-create-programmer';  
import { AdminEditProgrammerComponent } from './pages/admin-edit-programmer/admin-edit-programmer';

export const routes: Routes = [

  { path: '', redirectTo: 'home', pathMatch: 'full' },

  // =========================
  // PÚBLICO
  // =========================
  { path: 'home', component: HomeComponent },
  { path: 'login', component: loginComponent },

  // =========================
  // PERSONAS
  // =========================
  { path: 'persona/listado', component: PersonaComponent },
  { path: 'persona/crear', component: CrearPersonaComponent },
  { path: 'persona/editar/:cedula', component: EditarPersona },

  // =========================
  // CITAS
  // =========================
  { path: 'appointments/listado', component: AppointmentListComponent },
  { path: 'appointment-status/listado', component: AppointmentStatusComponent },

  // =========================
  // DISPONIBILIDAD
  // =========================
  { path: 'availability/crear', component: CreateAvailabilityComponent },
  { path: 'availability/list', component: ListAvailabilityComponent },

  // =========================
  // ADMIN
  // =========================
  {
    path: 'admin/dashboard',
    component: AdminDashboardComponent,
    canActivate: [AdminGuard]
  },

  // 👉 PRIMERO EDITAR (más específica)
  {
    path: 'admin/programmers/edit/:id',
    component: AdminEditProgrammerComponent,
    canActivate: [AdminGuard]
  },

  // 👉 LUEGO CREAR
  {
    path: 'admin/programmers/create',
    component: AdminCreateProgrammerComponent,
    canActivate: [AdminGuard]
  },

  // 👉 LUEGO LISTAR
  {
    path: 'admin/programmers',
    component: AdminProgrammersComponent,
    canActivate: [AdminGuard]
  },

  // =========================
  // PROGRAMADOR
  // =========================
  {
    path: 'programmer/dashboard',
    component: ProgrammerDashboardComponent,
    canActivate: [ProgrammerGuard]
  },

  {
    path: 'programmer/appointments',
    component: ProgrammerAppointmentsComponent,
    canActivate: [ProgrammerGuard]
  },

  // =========================
  // USUARIO
  // =========================
  { path: 'appointments/create', component: CreateAppointmentComponent },
  { path: 'appointments/my', component: MyAppointmentsComponent },

  // =========================
  // PORTFOLIO
  // =========================
  {
    path: 'portfolio/:id',
    component: ProgrammerPortfolioComponent
  },

  // =========================
  // NOT FOUND
  // =========================
  { path: '**', redirectTo: 'home' }

];
