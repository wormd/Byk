import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplyDialogAddComponent } from './supply-dialog-add.component';

describe('SupplyDialogAddComponent', () => {
  let component: SupplyDialogAddComponent;
  let fixture: ComponentFixture<SupplyDialogAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplyDialogAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplyDialogAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
