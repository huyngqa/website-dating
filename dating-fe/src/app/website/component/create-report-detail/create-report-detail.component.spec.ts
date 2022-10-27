import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateReportDetailComponent } from './create-report-detail.component';

describe('CreateReportDetailComponent', () => {
  let component: CreateReportDetailComponent;
  let fixture: ComponentFixture<CreateReportDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateReportDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateReportDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
