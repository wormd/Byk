import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupFormPageComponent } from './signup-form-page.component';

describe('SignupFormComponent', () => {
  let component: SignupFormPageComponent;
  let fixture: ComponentFixture<SignupFormPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupFormPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupFormPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
