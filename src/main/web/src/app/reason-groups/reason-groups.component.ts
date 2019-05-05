import {Component, OnInit} from "@angular/core";
import {ProblemService} from "../services/problem.service";
import {ReasonGroup} from "../model/reason-group";
import {isDefined} from "@angular/compiler/src/util";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: "app-reason-groups",
    templateUrl: "./reason-groups.component.html",
    styleUrls: ["./reason-groups.component.css"],
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
            this.selectedGroupId = params.parentId ? +params.parentId : undefined;
            this.loadReasonGroups();
        });
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
        let url = isDefined(reasonGroup.reasonId) ? "/createProblem" : "/";
        return this.router.navigate([url], {queryParams: {"parentId": reasonGroup.id}});
    }

    private loadReasonGroups() {
        this.problemService.getReasonGroups().subscribe(groups => this.groups = groups);
    }


}
