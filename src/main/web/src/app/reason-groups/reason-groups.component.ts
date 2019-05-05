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
    selectedGroup: ReasonGroup;
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
        });
        this.loadSelectedReasonGroup();
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
        if (this.selectedGroup) {
            let parentId = this.selectedGroup.parent ? this.selectedGroup.parent.id : undefined;
            this.router.navigate(["/"], {queryParams: {"parentId": parentId}});
        }
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

    private loadSelectedReasonGroup() {
        if (isDefined(this.selectedGroupId)) {
            this.problemService.getReasonGroup(this.selectedGroupId).subscribe(reasonGroup => {
                this.selectedGroup = reasonGroup;
            });
        }
    }

}
