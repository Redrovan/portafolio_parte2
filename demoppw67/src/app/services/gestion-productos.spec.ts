import { TestBed } from '@angular/core/testing';

import { GestionProductos } from './gestion-productos';

describe('GestionProductos', () => {
  let service: GestionProductos;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionProductos);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
