import {Component, OnInit} from '@angular/core';
import {ProblemService} from "../services/problem.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CreateProblemRequest} from "../model/create-problem-request";

@Component({
    selector: 'app-create-problem',
    templateUrl: './create-problem.component.html',
    styleUrls: ['./create-problem.component.css']
})
export class CreateProblemComponent implements OnInit {

    model = new CreateProblemRequest();

    constructor(
        private problemService: ProblemService,
        private router: Router,
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            this.model.reasonGroupId = Number(params.reasonGroupId);
        });
        navigator.geolocation.getCurrentPosition(position => {
            this.model.latitude = position.coords.latitude;
            this.model.longitude = position.coords.longitude;
        })
    }

    onFileSelect(event) {
        let files = event.target.files;
        if (files.length > 0) {
            for (let i = 0; i < files.length; i++) {
                let file = files[i];
                this.model.files.push(file);
            }
            // files.forEach(file => this.model.files.push[file]);
            // const file = event.target.files[0];
            // this.uploadForm.get('profile').setValue(file);
        }
    }

    doSend() {
        this.problemService.createProblem(this.model).subscribe(problem => console.log(problem));
    }

}
