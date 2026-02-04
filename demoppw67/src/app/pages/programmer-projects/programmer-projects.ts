import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackButtonComponent } from '../back-button/back-button';
import { ProjectService } from '../../services/project.service';
import { AuthService } from '../../services/auth.service';
import { Project } from '../../domain/models';

@Component({
  standalone: true,
  selector: 'app-programmer-projects',
  imports: [CommonModule, FormsModule, BackButtonComponent],
  templateUrl: './programmer-projects.html',
  styleUrls: ['./programmer-projects.scss']
})
export class ProgrammerProjectsComponent implements OnInit {

  academic: Project[] = [];
  professional: Project[] = [];

  userId!: number;

  showForm = false;

  newProject: Project = {
  name: '',
  description: '',
  section: 'ACADEMICO',
  technologies: '',
  repositoryUrl: '',
  deployUrl: ''
};


  constructor(
    private projectService: ProjectService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {

    this.userId = this.authService.user?.id!;

    if (!this.userId) return;

    this.loadProjects();
  }

  loadProjects() {

    this.projectService.getProjectsByUser(this.userId).subscribe({
      next: projects => {

        this.academic = projects.filter(p => p.section === 'ACADEMICO');
        this.professional = projects.filter(p => p.section === 'LABORAL');

      }
    });
  }

  saveProject() {

    const projectToSend: Project = {
      ...this.newProject,
      owner: { id: this.userId },
      active: true
    };

    this.projectService.createProject(projectToSend).subscribe({
      next: () => {

        this.newProject = {
          name: '',
          description: '',
          section: 'ACADEMICO',
          technologies: '',
          repositoryUrl: '',
          deployUrl: ''
        };

        this.showForm = false;
        this.loadProjects();
      }
    });
  }

  deleteProject(id?: number) {

    if (!id) return;

    this.projectService.deleteProject(id).subscribe({
      next: () => this.loadProjects()
    });
  }

}
