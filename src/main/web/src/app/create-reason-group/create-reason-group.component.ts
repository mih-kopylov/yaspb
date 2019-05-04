import {Component, OnInit} from '@angular/core';
import {ProblemService} from "../services/problem.service";
import {CreateReasonGroupRequest} from "../model/create-reason-group-request";
import {ReasonGroup} from "../model/reason-group";
import {isDefined} from "@angular/compiler/src/util";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-create-reason-group',
    templateUrl: './create-reason-group.component.html',
    styleUrls: ['./create-reason-group.component.css']
})
export class CreateReasonGroupComponent implements OnInit {
    private reasonsMap = new Map<string, InnerReason[]>();
    private reasonsMapKeys: string[] = [];
    private request = new CreateReasonGroupRequest();
    private reasonGroups: ReasonGroup[] = [];

    constructor(
        private problemService: ProblemService,
        private router: Router,
        private route: ActivatedRoute,
    ) {
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
                        reasonsInGroup.push(new InnerReason(reason.id, groupName, reason.name))
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
        this.problemService.createReasonGroup(this.request).subscribe(()=>{
            this.router.navigate(["/"], {queryParams: {"parentId": this.request.parentId}});
        })
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