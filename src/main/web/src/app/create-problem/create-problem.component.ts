import {Component, OnInit} from "@angular/core";
import {Location} from "@angular/common";
import {ProblemService} from "../services/problem.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CreateProblemRequest} from "../model/create-problem-request";
import {GeoService} from "../services/geo.service";
import {MatSnackBar} from "@angular/material";
import {finalize} from "rxjs/operators";

@Component({
    selector: "app-create-problem",
    templateUrl: "./create-problem.component.html",
    styleUrls: ["./create-problem.component.css"],
})
export class CreateProblemComponent implements OnInit {
    creating = false;
    model = new CreateProblemRequest();
    files: SelectedFile[] = [];

    constructor(
        private problemService: ProblemService,
        private router: Router,
        private route: ActivatedRoute,
        private geoService: GeoService,
        private snackBar: MatSnackBar,
        private location: Location,
    ) {
    }

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            this.model.reasonGroupId = +params.parentId;
        });
    }

    onFileSelect(event) {
        let files = event.target.files;
        if (files.length > 0) {
            for (let i = 0; i < files.length; i++) {
                let file = files[i];
                let selectedFile = new SelectedFile();
                selectedFile.file = file;
                this.files.push(selectedFile);

                let reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = () => {
                    return selectedFile.preview = reader.result;
                };
            }
        }
    }

    deleteFile(file) {
        this.files.splice(this.files.indexOf(file), 1);
    }

    doSend() {
        this.model.latitude = this.geoService.getCoords().latitude;
        this.model.longitude = this.geoService.getCoords().longitude;
        for (const file of this.files) {
            this.model.files.push(file.file);
        }
        this.creating = true;
        this.problemService.createProblem(this.model).pipe(
            finalize(() => this.creating = false),
        ).subscribe(problem => {
                this.snackBar.open("Обращение " + problem.id + " создано", "Открыть")
                    .afterDismissed().subscribe((dismissReason) => {
                    if (dismissReason.dismissedByAction) {
                        window.open("https://gorod.gov.spb.ru/problems/" + problem.id, "_blank");
                    }
                });
                this.location.back();
            },
        );
    }

}

class SelectedFile {
    file: File;
    preview: string | ArrayBuffer;
}