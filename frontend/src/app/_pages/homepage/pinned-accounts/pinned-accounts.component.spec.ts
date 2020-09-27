import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PinnedAccountsComponent } from './pinned-accounts.component';

describe('PinnedAccountsComponent', () => {
  let component: PinnedAccountsComponent;
  let fixture: ComponentFixture<PinnedAccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PinnedAccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PinnedAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
