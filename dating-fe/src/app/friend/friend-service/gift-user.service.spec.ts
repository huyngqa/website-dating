import { TestBed } from '@angular/core/testing';

import { GiftUserService } from './gift-user.service';

describe('GiftUserService', () => {
  let service: GiftUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GiftUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
