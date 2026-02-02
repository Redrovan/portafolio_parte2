import { Role, Persona } from "./models";

export interface UserWithToken {
  token: string;
  role: Role | string;
  email: string;
  status: string;
  id?: number;
  persona?: Persona;  // <-- AÑADIR ESTA LÍNEA
}
