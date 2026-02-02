// =======================
// PERSONA
// =======================
export interface Persona {
  cedula: string;
  nombre: string;
  direccion: string;
}

// =======================
// ROLE
// =======================
export enum Role {
  ADMIN = 'ADMIN',
  USER = 'USER',
  PROGRAMADOR = 'PROGRAMMER'
}

// =======================
// ESPECIALIDAD
// =======================
export interface Especialidad {
  id?: number;
  nombre: string;
  descripcion: string;
}

// =======================
// PROGRAMMER PROFILE
// =======================
export interface ProgrammerProfile {
  id?: number;
  bio: string;
  experienceYears: number;
  photoUrl: string;
  socialLinks: string;
}

// =======================
// USER
// =======================
export interface User {
  id?: number;
  email: string;
  role: Role;
  active?: boolean;

  photoUrl?: string;
  phone?: string;
  socialLinks?: string;

  persona?: Persona;
  especialidad?: Especialidad;
  programmerProfile?: ProgrammerProfile;
}

// =======================
// PROJECT
// =======================
export interface Project {
  id: number;
  name: string;
  description: string;
  repositoryUrl?: string;
  deployUrl?: string;
  technologies?: string;
  section?: string;
  participationType?: any;
  active?: boolean;
  owner?: any;
}

// =======================
// APPOINTMENT STATUS
// =======================
export interface AppointmentStatus {
  id: number;
  name: 'PENDING' | 'APPROVED' | 'REJECTED';
}

// =======================
// APPOINTMENT
// =======================
export interface Appointment {
  id?: number;
  client: User;
  programmer: User;
  date: string;
  time: string;
  status: AppointmentStatus;
  comment?: string;
  mode?: string;
}

// =======================
// AVAILABILITY
// =======================
export interface Availability {
  id?: number;
  day: string;
  startTime: string;
  endTime: string;
  mode?: string;
  programmer: User;
}

// =======================
// API ERROR
// =======================
export interface ApiError {
  codigo: number;
  name: string;
  descripcion: string;
}
