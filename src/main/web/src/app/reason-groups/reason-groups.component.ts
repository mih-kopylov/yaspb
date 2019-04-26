import {Component, OnInit} from '@angular/core';
import {ProblemService} from "../services/problem.service";
import {ReasonGroup} from "../model/reason-group";
import {isDefined} from "@angular/compiler/src/util";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-reason-groups',
    templateUrl: './reason-groups.component.html',
    styleUrls: ['./reason-groups.component.css']
})
export class ReasonGroupsComponent implements OnInit {

    selectedGroupId: number;
    groups: ReasonGroup[] = [];

    constructor(
        private problemService: ProblemService,
        private router: Router,
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            this.selectedGroupId = params.parentId ? Number(params.parentId) : undefined;
        });
        this.loadReasonGroups();
    }

    getReasonGroupsByParent(parentGroupId: number): ReasonGroup[] {
        return this.groups.filter(o => {
            if (!isDefined(parentGroupId)) {
                return !isDefined(o.parent);
            }
            return isDefined(o.parent) && o.parent.id === parentGroupId;
        });
    }

    select(reasonGroup: ReasonGroup) {
        this.router.navigate(["/"], {queryParams: {"parentId": reasonGroup.id}});
    }

    getGroupById(groupId): ReasonGroup {
        if (!isDefined(groupId)) {
            return undefined;
        }
        return this.groups.filter(o => o.id === groupId)[0];
    }

    private loadReasonGroups() {
        this.problemService.getReasonGroups().subscribe(groups => this.groups = groups);
    }

}
