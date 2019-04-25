import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Api} from "./api";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(
        private httpClient: HttpClient
    ) {
    }

    login(login: string, password: string): Observable<any> {
        const body = {login, password};
        return this.httpClient.post(Api.LOGIN, body).pipe(response => {
            console.log(response);
            return response;
        });
    }
}
