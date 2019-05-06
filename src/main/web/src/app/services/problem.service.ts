import {Injectable} from "@angular/core";
import {Observable, throwError} from "rxjs";
import {ReasonGroup} from "../model/reason-group";
import {HttpClient} from "@angular/common/http";
import {Api} from "./api";
import {CreateProblemRequest} from "../model/create-problem-request";
import {Problem} from "../model/problem";
import {CityObject} from "../model/reason";
import {CreateReasonGroupRequest} from "../model/create-reason-group-request";
import {catchError} from "rxjs/operators";
import {MatSnackBar} from "@angular/material";

@Injectable({
    providedIn: "root",
})
export class ProblemService {

    constructor(
        private httpClient: HttpClient,
        private snackBar: MatSnackBar,
    ) {
    }

    getReasonGroups(): Observable<ReasonGroup[]> {
        return this.httpClient.get<ReasonGroup[]>(Api.REASON_GROUPS);
    }

    createReasonGroup(request: CreateReasonGroupRequest): Observable<ReasonGroup> {
        return this.httpClient.post<ReasonGroup>(Api.REASON_GROUPS, request);
    }

    updateReasonGroup(reasonGroupId: number, request: CreateReasonGroupRequest): Observable<ReasonGroup> {
        return this.httpClient.put<ReasonGroup>(Api.REASON_GROUPS + "/" + reasonGroupId, request);
    }

    getReasons(): Observable<CityObject[]> {
        return this.httpClient.get<CityObject[]>(Api.REASONS);
    }

    createProblem(model: CreateProblemRequest): Observable<Problem> {
        let body = new FormData();
        body.append("reasonGroupId", model.reasonGroupId.toString());
        body.append("latitude", model.latitude.toString());
        body.append("longitude", model.longitude.toString());
        model.files.forEach(file => body.append("files", file, file.name));
        return this.httpClient.post<Problem>(Api.PROBLEM, body).pipe(
            catchError(error => {
                this.snackBar.open("Не удалось создать обращение");
                return throwError(error);
            })
        );
    }

    deleteReasonGroup(reasonGroupId: number): Observable<any> {
        return this.httpClient.delete(Api.REASON_GROUPS + "/" + reasonGroupId);
    }

    getReasonGroup(reasonGroupId: number): Observable<ReasonGroup> {
        return this.httpClient.get<ReasonGroup>(Api.REASON_GROUPS + "/" + reasonGroupId);
    }
}
