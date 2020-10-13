import { TestBed } from '@angular/core/testing';

import { SupplyService } from './supply.service';

describe('SupplyServiceService', () => {
  let service: SupplyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupplyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
