import { TestBed, inject } from '@angular/core/testing';

import { ApartmentDataService } from './apartment-data.service';

describe('ApartmentDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApartmentDataService]
    });
  });

  it('should be created', inject([ApartmentDataService], (service: ApartmentDataService) => {
    expect(service).toBeTruthy();
  }));
});
