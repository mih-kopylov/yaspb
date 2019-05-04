import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateReasonGroupComponent } from './create-reason-group.component';

describe('CreateReasonGroupComponent', () => {
  let component: CreateReasonGroupComponent;
  let fixture: ComponentFixture<CreateReasonGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateReasonGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateReasonGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
