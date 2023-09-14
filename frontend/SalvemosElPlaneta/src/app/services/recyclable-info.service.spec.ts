import { TestBed } from '@angular/core/testing';

import { RecyclableInfoService } from './recyclable-info.service';

describe('RecyclableInfoService', () => {
  let service: RecyclableInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecyclableInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
