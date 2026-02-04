import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { BackButtonComponent } from '../back-button/back-button';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { ProjectService } from '../../services/project.service';

import { User, Project } from '../../domain/models';

@Component({
  selector: 'app-programmer-portfolio',
  standalone: true,
  imports: [CommonModule, RouterModule, BackButtonComponent ],
  templateUrl: './programmer-portfolio.html',
  styleUrls: ['./programmer-portfolio.scss']
})
export class ProgrammerPortfolioComponent implements OnInit {

  programmer?: User;
  projects: Project[] = [];

  authUser: any;

  loadingProgrammer = true;
  loadingProjects = true;

  errorProgrammer = '';
  errorProjects = '';

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private projectService: ProjectService,
    private auth: AuthService
  ) {}

  ngOnInit(): void {

    // Usuario logueado (para controlar botones por rol)
    this.authUser = this.auth.user;

    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) return;

    // 👤 PROGRAMADOR (PÚBLICO)
    this.userService.getPublicById(id).subscribe({
      next: data => {
        this.programmer = data;
        this.loadingProgrammer = false;
      },
      error: err => {
        console.error(err);
        this.errorProgrammer = 'No se pudo cargar el programador';
        this.loadingProgrammer = false;
      }
    });

    // PROYECTOS (PÚBLICO)
    this.projectService.getPublicProjectsByUser(id).subscribe({
      next: data => {
        this.projects = data;
        this.loadingProjects = false;
      },
      error: err => {
        console.error(err);
        this.errorProjects = 'No se pudieron cargar los proyectos';
        this.loadingProjects = false;
      }
    });

  }

}
