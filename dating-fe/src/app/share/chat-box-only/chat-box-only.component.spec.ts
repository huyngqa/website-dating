import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatBoxOnlyComponent } from './chat-box-only.component';

describe('ChatBoxOnlyComponent', () => {
  let component: ChatBoxOnlyComponent;
  let fixture: ComponentFixture<ChatBoxOnlyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChatBoxOnlyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatBoxOnlyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
