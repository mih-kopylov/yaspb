import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ReasonGroupsComponent} from './reason-groups.component';

describe('ReasonGroupsComponent', () => {
    let component: ReasonGroupsComponent;
    let fixture: ComponentFixture<ReasonGroupsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ReasonGroupsComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ReasonGroupsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
