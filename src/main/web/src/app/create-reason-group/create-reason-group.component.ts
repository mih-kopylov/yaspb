import {Component, OnInit} from "@angular/core";
import {ProblemService} from "../services/problem.service";
import {CreateReasonGroupRequest} from "../model/create-reason-group-request";
import {ReasonGroup} from "../model/reason-group";
import {isDefined} from "@angular/compiler/src/util";
import {ActivatedRoute, Router} from "@angular/router";
import {finalize} from "rxjs/operators";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: "app-create-reason-group",
    templateUrl: "./create-reason-group.component.html",
    styleUrls: ["./create-reason-group.component.css"],
})
export class CreateReasonGroupComponent implements OnInit {
    reasonsMapKeys: string[] = [];
    request = new CreateReasonGroupRequest();
    reasonGroups: ReasonGroup[] = [];
    creation: boolean = false;
    private reasonsMap = new Map<string, InnerReason[]>();

    constructor(
        private problemService: ProblemService,
        private router: Router,
        private route: ActivatedRoute,
        private snackBar: MatSnackBar,
    ) {
    }

    private static getErrorMessageByStatus(status: number): string {
        if (status === 409) {
            return "Такое название уже существует";
        } else {
            return "Произошла ошибка";
        }
    }

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            this.request.parentId = params.parentId ? Number(params.parentId) : undefined;
        });
        this.problemService.getReasons().subscribe(cityObjects => {
            for (const cityObject of cityObjects) {
                let groupName = cityObject.name;
                let reasonsInGroup: InnerReason[] = [];
                for (const category of cityObject.categories) {
                    for (const reason of category.reasons) {
                        reasonsInGroup.push(new InnerReason(reason.id, groupName, reason.name));
                    }
                }
                reasonsInGroup = reasonsInGroup.sort((a, b) => a.name.localeCompare(b.name));
                this.reasonsMap.set(groupName, reasonsInGroup);
            }
            this.reasonsMapKeys = Array.from(this.reasonsMap.keys()).sort();
        });
        this.problemService.getReasonGroups().subscribe(groups => this.reasonGroups = groups.filter(g => !isDefined(g.reasonId)));
    }

    create() {
        this.creation = true;
        this.problemService.createReasonGroup(this.request)
            .pipe(
                finalize(() => this.creation = false))
            .subscribe(() => this.router.navigate(["/"], {queryParams: {"parentId": this.request.parentId}}),
                error => {
                    let message: string = CreateReasonGroupComponent.getErrorMessageByStatus(error.status);
                    this.snackBar.open(message);
                });
    }
}

class InnerReason {
    id: number;
    group: string;
    name: string;

    constructor(id: number, group: string, name: string) {
        this.id = id;
        this.group = group;
        this.name = name;
    }

}