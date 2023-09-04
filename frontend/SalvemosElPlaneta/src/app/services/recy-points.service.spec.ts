import { TestBed } from '@angular/core/testing';

import { RecyPointsService } from './recy-points.service';

describe('RecyPointsService', () => {
  let service: RecyPointsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecyPointsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
