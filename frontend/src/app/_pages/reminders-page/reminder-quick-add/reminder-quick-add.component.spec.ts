import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReminderQuickAddComponent } from './reminder-quick-add.component';

describe('ReminderQuickAddComponent', () => {
  let component: ReminderQuickAddComponent;
  let fixture: ComponentFixture<ReminderQuickAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReminderQuickAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReminderQuickAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
