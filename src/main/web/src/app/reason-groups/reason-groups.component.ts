import {Component, OnInit} from "@angular/core";
import {ProblemService} from "../services/problem.service";
import {ReasonGroup} from "../model/reason-group";
import {isDefined} from "@angular/compiler/src/util";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";

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
        private location: Location,
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

    navigateBack() {
        this.location.back();
    }

    edit() {

    }

    delete() {
        if (confirm("Удалить?")) {
            this.problemService.deleteReasonGroup(this.selectedGroupId).subscribe(() => {
                this.loadReasonGroups();
                this.navigateBack();
            });
        }
    }

    select(reasonGroup: ReasonGroup) {
        if (isDefined(reasonGroup.reasonId)) {
            this.router.navigate(["/createProblem"], {queryParams: {"reasonGroupId": reasonGroup.id}});
        } else {
            this.router.navigate(["/"], {queryParams: {"parentId": reasonGroup.id}});
        }
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
