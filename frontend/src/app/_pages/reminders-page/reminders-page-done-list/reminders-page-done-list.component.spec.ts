import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemindersPageDoneListComponent } from './reminders-page-done-list.component';

describe('RemindersPageDoneListComponent', () => {
  let component: RemindersPageDoneListComponent;
  let fixture: ComponentFixture<RemindersPageDoneListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemindersPageDoneListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemindersPageDoneListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
