import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ReasonGroup} from "../model/reason-group";
import {HttpClient} from "@angular/common/http";
import {Api} from "./api";
import {CreateProblemRequest} from "../model/create-problem-request";
import {Problem} from "../model/problem";

@Injectable({
    providedIn: 'root'
})
export class ProblemService {

    constructor(
        private httpClient: HttpClient
    ) {
    }

    getReasonGroups(): Observable<ReasonGroup[]> {
        return this.httpClient.get<ReasonGroup[]>(Api.REASON_GROUPS);
    }

    createProblem(model: CreateProblemRequest): Observable<Problem> {
        let body = new FormData();
        body.append("reasonGroupId", model.reasonGroupId.toString());
        body.append("latitude", model.latitude.toString());
        body.append("longitude", model.longitude.toString());
        model.files.forEach(file => body.append("files", file, file.name));
        return this.httpClient.post<Problem>(Api.PROBLEM, body)
    }
}
