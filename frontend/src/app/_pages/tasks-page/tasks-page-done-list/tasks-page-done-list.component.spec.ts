import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksPageDoneListComponent } from './tasks-page-done-list.component';

describe('RemindersPageDoneListComponent', () => {
  let component: TasksPageDoneListComponent;
  let fixture: ComponentFixture<TasksPageDoneListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasksPageDoneListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasksPageDoneListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
