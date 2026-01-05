import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroPersona } from './registro-persona';

describe('RegistroPersona', () => {
  let component: RegistroPersona;
  let fixture: ComponentFixture<RegistroPersona>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistroPersona]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroPersona);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
