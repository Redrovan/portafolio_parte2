import { TestBed } from '@angular/core/testing';

import { GestionSimpsons } from './gestion-simpsons';

describe('GestionSimpsons', () => {
  let service: GestionSimpsons;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionSimpsons);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
