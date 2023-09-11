import { TestBed } from '@angular/core/testing';

import { RewardTransactionsService } from './reward-transactions.service';

describe('RewardTransactionsService', () => {
  let service: RewardTransactionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RewardTransactionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
