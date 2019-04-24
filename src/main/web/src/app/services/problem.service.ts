import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ReasonGroup} from "../model/reason-group";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class ProblemService {

    constructor(
        private httpClient: HttpClient
    ) {
    }

    getReasonGroups(): Observable<ReasonGroup[]> {
        return this.httpClient.get<ReasonGroup[]>("http://localhost:8080/rest/reasonGroups1");
    }
}
