import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeDialogEditComponent } from './employee-dialog-edit.component';

describe('EmployeeDialogEditComponent', () => {
  let component: EmployeeDialogEditComponent;
  let fixture: ComponentFixture<EmployeeDialogEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDialogEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDialogEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
