import { Routes } from '@angular/router';
import { RegistroProducto } from './pages/registro-producto/registro-producto';
import { Formulario } from './components/formulario/formulario';
import { Listado } from './pages/listado/listado';
import { PersonajesSimpsons } from './pages/personajes-simpsons/personajes-simpsons';
import { RegistroPersona } from './pages/registro-persona/registro-persona';
import { PersonaComponent } from './components/persona.component';
import { AppointmentListComponent } from './components/appointment-list.component';
import { AppointmentStatusComponent } from './components/appointment-status.component';
import { CrearPersonaComponent } from './components/crear-persona/crear-persona';
import { EditarPersona } from './components/editar-persona/editar-persona';

export const routes: Routes = [
    { path: '', redirectTo: 'inicio', pathMatch: 'full'},
    { path: 'inicio', component: Formulario},
    { path: 'producto/registrar', component: RegistroProducto},
    { path: 'producto/editar/:id', component: RegistroProducto},
    { path: 'producto/listado', component: Listado},
    { path: 'simpsons/listado', component: PersonajesSimpsons},
    { path: 'persona/registrar', component: RegistroPersona},
    { path: 'persona/listado', component: PersonaComponent },
    { path: 'appointments/listado', component: AppointmentListComponent },
    { path: 'appointment-status/listado', component: AppointmentStatusComponent},
    { path: 'persona/editar/:cedula', component: EditarPersona },
    { path: 'persona/crear', component: CrearPersonaComponent },

];
