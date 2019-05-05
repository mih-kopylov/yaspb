import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentReasonGroupComponent } from './parent-reason-group.component';

describe('ParentReasonGroupComponent', () => {
  let component: ParentReasonGroupComponent;
  let fixture: ComponentFixture<ParentReasonGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentReasonGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentReasonGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
