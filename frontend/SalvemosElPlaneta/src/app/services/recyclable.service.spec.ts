import { TestBed } from '@angular/core/testing';

import { RecyclableService } from './recyclable.service';

describe('RecyclableService', () => {
  let service: RecyclableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecyclableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
