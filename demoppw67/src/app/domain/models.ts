export interface Persona {
  cedula: string;
  nombre: string;
  direccion: string;
}

export interface ApiError {
  codigo: number;
  name: string;
  descripcion: string;
}

export interface Appointment {
  id: number;
  client: any;
  programmer: any;
  date: string;
  time: string;
  status: any;
}

export interface AppointmentStatus {
  id: number;
  name: string;
}
