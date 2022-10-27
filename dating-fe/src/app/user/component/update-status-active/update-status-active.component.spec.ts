import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateStatusActiveComponent } from './update-status-active.component';

describe('UpdateStatusActiveComponent', () => {
  let component: UpdateStatusActiveComponent;
  let fixture: ComponentFixture<UpdateStatusActiveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateStatusActiveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateStatusActiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
