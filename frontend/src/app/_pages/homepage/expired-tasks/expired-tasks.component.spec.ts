import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpiredTasksComponent } from './expired-tasks.component';

describe('ExpiredRemindersComponent', () => {
  let component: ExpiredTasksComponent;
  let fixture: ComponentFixture<ExpiredTasksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpiredTasksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpiredTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
