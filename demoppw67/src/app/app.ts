import { Component, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Formulario } from './components/formulario/formulario';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  title = "Cristian Timbi";
  edad = 40;

  saludar(){
    this.title = "Usted tiene " + this.edad;
    this.edad += 1;
  }
}
