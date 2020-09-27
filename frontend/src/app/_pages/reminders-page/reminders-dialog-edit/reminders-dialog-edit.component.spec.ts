import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemindersDialogEditComponent } from './reminders-dialog-edit.component';

describe('RemindersDialogEditComponent', () => {
  let component: RemindersDialogEditComponent;
  let fixture: ComponentFixture<RemindersDialogEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemindersDialogEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemindersDialogEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
