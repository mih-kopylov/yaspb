import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ImportReasonGroupsRequest} from "../../model/import-reason-groups-request";

@Component({
    selector: "import-reason-groups-dialog",
    templateUrl: "./import-reason-groups-dialog.component.html",
    styleUrls: ["./import-reason-groups-dialog.component.css"],
})
export class ImportReasonGroupsDialogComponent {

    constructor(
        public dialogRef: MatDialogRef<ImportReasonGroupsDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: ImportReasonGroupsRequest) {
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

}
