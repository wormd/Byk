import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceQuickAddComponent } from './service-quick-add.component';

describe('ServiceQuickAddComponent', () => {
  let component: ServiceQuickAddComponent;
  let fixture: ComponentFixture<ServiceQuickAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceQuickAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceQuickAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
