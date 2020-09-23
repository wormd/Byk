import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemindersPageListComponent } from './reminders-page-list.component';

describe('RemindersPageListComponent', () => {
  let component: RemindersPageListComponent;
  let fixture: ComponentFixture<RemindersPageListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemindersPageListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemindersPageListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
