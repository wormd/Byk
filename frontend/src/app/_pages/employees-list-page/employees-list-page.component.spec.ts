import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EmployeesListPageComponent} from './employees-list-page.component';

describe('EmployeeListComponent', () => {
  let component: EmployeesListPageComponent;
  let fixture: ComponentFixture<EmployeesListPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeesListPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeesListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
