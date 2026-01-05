import { TestBed } from '@angular/core/testing';

import { GestionPersonas } from './gestion-personas';

describe('GestionPersonas', () => {
  let service: GestionPersonas;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionPersonas);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
