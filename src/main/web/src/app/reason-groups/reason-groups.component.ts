import {Component, OnInit} from '@angular/core';
import {ProblemService} from "../services/problem.service";
import {ReasonGroup} from "../model/reason-group";

@Component({
    selector: 'app-reason-groups',
    templateUrl: './reason-groups.component.html',
    styleUrls: ['./reason-groups.component.css']
})
export class ReasonGroupsComponent implements OnInit {

    groups: ReasonGroup[];

    constructor(
        private problemService: ProblemService,
    ) {
    }

    ngOnInit() {
        // this.getReasonGroups();
    }

    getReasonGroups() {
        this.problemService.getReasonGroups().subscribe(groups => this.groups = groups);
    }

}
