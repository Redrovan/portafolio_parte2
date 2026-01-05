import { ComponentFixture, TestBed } from '@angular/core/testing';

import {CrearPersonaComponent} from './crear-persona';

describe('CrearPersona', () => {
  let component: CrearPersonaComponent;
  let fixture: ComponentFixture<CrearPersonaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearPersonaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearPersonaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
