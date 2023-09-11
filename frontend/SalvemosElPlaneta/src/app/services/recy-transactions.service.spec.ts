import { TestBed } from '@angular/core/testing';

import { RecyTransactionsService } from './recy-transactions.service';

describe('RecyTransactionsService', () => {
  let service: RecyTransactionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecyTransactionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
