import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksDialogEditComponent } from './tasks-dialog-edit.component';

describe('RemindersDialogEditComponent', () => {
  let component: TasksDialogEditComponent;
  let fixture: ComponentFixture<TasksDialogEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasksDialogEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasksDialogEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
