import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginFormPageComponent} from './login-form-page.component';

describe('LoginFormComponent', () => {
  let component: LoginFormPageComponent;
  let fixture: ComponentFixture<LoginFormPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginFormPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginFormPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
