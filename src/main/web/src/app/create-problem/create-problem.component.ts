import {Component, OnInit} from "@angular/core";
import {Location} from "@angular/common";
import {ProblemService} from "../services/problem.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CreateProblemRequest} from "../model/create-problem-request";
import {GeoService} from "../services/geo.service";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: "app-create-problem",
    templateUrl: "./create-problem.component.html",
    styleUrls: ["./create-problem.component.css"],
})
export class CreateProblemComponent implements OnInit {

    model = new CreateProblemRequest();

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
            this.model.reasonGroupId = Number(params.reasonGroupId);
        });
    }

    onFileSelect(event) {
        let files = event.target.files;
        if (files.length > 0) {
            for (let i = 0; i < files.length; i++) {
                let file = files[i];
                this.model.files.push(file);
            }
        }
    }

    doSend() {
        this.model.latitude = this.geoService.getCoords().latitude;
        this.model.longitude = this.geoService.getCoords().longitude;
        this.problemService.createProblem(this.model).subscribe(problem => {
                this.snackBar.open("Проблема " + problem.id + " создана", "Открыть", {
                    duration: 3000,
                }).afterDismissed().subscribe((dismissReason) => {
                    if (dismissReason.dismissedByAction) {
                        window.open("https://gorod.gov.spb.ru/problems/" + problem.id, "_blank");
                    }
                });
                this.location.back();
            },
        );
    }

}
