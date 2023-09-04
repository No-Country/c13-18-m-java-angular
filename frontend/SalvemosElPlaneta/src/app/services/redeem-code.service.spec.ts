import { TestBed } from '@angular/core/testing';

import { RedeemCodeService } from './redeem-code.service';

describe('RedeemCodeService', () => {
  let service: RedeemCodeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RedeemCodeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
