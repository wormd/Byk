import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksPageListComponent } from './tasks-page-list.component';

describe('RemindersPageListComponent', () => {
  let component: TasksPageListComponent;
  let fixture: ComponentFixture<TasksPageListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasksPageListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasksPageListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
