import {Component, Input, OnInit} from "@angular/core";
import {ReasonGroup} from "../model/reason-group";
import {isDefined} from "@angular/compiler/src/util";
import {ProblemService} from "../services/problem.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: "app-parent-reason-group",
    templateUrl: "./parent-reason-group.component.html",
    styleUrls: ["./parent-reason-group.component.css"],
})
export class ParentReasonGroupComponent implements OnInit {
    @Input() groupId: number;
    group: ReasonGroup;

    constructor(
        private problemService: ProblemService,
        private router: Router,
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            this.groupId = params.parentId ? +params.parentId : undefined;
            this.loadSelectedReasonGroup(this.groupId);
        });
    }

    navigateBack() {
        if (this.group) {
            let parentId = this.group.parent ? this.group.parent.id : undefined;
            this.router.navigate(["/"], parentId ? {queryParams: {"parentId": parentId}} : {});
        }
    }

    delete() {
        if (confirm("Удалить?")) {
            this.problemService.deleteReasonGroup(this.groupId).subscribe(() => {
                this.navigateBack();
            });
        }
    }

    private loadSelectedReasonGroup(id: number) {
        if (isDefined(id)) {
            this.problemService.getReasonGroup(id).subscribe(reasonGroup => {
                this.group = reasonGroup;
            });
        }
    }
}
