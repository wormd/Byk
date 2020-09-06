import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuppliesPageComponent } from './supplies-page.component';

describe('SuppliesPageComponent', () => {
  let component: SuppliesPageComponent;
  let fixture: ComponentFixture<SuppliesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuppliesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuppliesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
