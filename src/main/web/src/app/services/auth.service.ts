import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Api} from "./api";
import {Token} from "../model/token";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(
        private httpClient: HttpClient
    ) {
    }

    static loadToken(): Token {
        let storedToken = localStorage.getItem("token");
        if (storedToken) {
            return Object.assign(new Token(), JSON.parse(storedToken));
        }
    }

    static deleteToken() {
        localStorage.removeItem("token");
    }

    static saveToken(token: Token) {
        localStorage.setItem("token", JSON.stringify(token));
    }

    login(login: string, password: string): Observable<any> {
        const body = {login, password};
        return this.httpClient.post(Api.LOGIN, body);
    }

}
