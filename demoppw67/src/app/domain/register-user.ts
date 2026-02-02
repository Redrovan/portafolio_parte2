import { Role } from "./models";

export interface RegisterUser {
  email: string;
  password: string;
  role: Role;
}
