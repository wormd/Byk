import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpiredRemindersComponent } from './expired-reminders.component';

describe('ExpiredRemindersComponent', () => {
  let component: ExpiredRemindersComponent;
  let fixture: ComponentFixture<ExpiredRemindersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpiredRemindersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpiredRemindersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
