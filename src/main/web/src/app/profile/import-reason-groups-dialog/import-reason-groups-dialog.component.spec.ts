import {async, ComponentFixture, TestBed} from "@angular/core/testing";

import {ImportReasonGroupsDialogComponent} from "./import-reason-groups-dialog.component";

describe("ImportReasonGroupsDialogComponent", () => {
    let component: ImportReasonGroupsDialogComponent;
    let fixture: ComponentFixture<ImportReasonGroupsDialogComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ImportReasonGroupsDialogComponent],
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ImportReasonGroupsDialogComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
