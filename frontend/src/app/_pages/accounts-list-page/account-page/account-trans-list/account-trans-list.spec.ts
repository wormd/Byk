import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccountTransListComponent} from './account-trans-list.component';

describe('AccountComponent', () => {
  let component: AccountTransListComponent;
  let fixture: ComponentFixture<AccountTransListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountTransListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountTransListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
