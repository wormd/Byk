import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EmployeeDialogAddComponent} from './employee-dialog-add.component';

describe('EmployeeFormComponent', () => {
  let component: EmployeeDialogAddComponent;
  let fixture: ComponentFixture<EmployeeDialogAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDialogAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDialogAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
