import { TestBed } from '@angular/core/testing';

import { ReportDetailService } from './report-detail.service';

describe('ReportDetailService', () => {
  let service: ReportDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
