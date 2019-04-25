import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Api} from "./api";
import {tap} from "rxjs/operators";
import {Token} from "../model/token";

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
        return this.httpClient.post(Api.LOGIN, body).pipe(
            tap(token => console.log(token)),
            tap(AuthService.saveToken)
        );
    }

    static saveToken(token: Token) {
        localStorage.setItem("token", JSON.stringify(token));
    }

    static loadToken(): Token {
        let storedToken = localStorage.getItem("token");
        if (storedToken) {
            return JSON.parse(storedToken);
        }
        return new Token();
    }
}
