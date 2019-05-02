import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Api} from "./api";
import {Token} from "../model/token";
import {map} from "rxjs/operators";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    authEvents = new EventEmitter();

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

    static saveToken(token: Token) {
        localStorage.setItem("token", JSON.stringify(token));
    }

    logout() {
        localStorage.removeItem("token");
        this.authEvents.emit("logout");
    }

    login(login: string, password: string): Observable<any> {
        const body = {login, password};
        return this.httpClient.post(Api.LOGIN, body).pipe(map(() => this.authEvents.emit("login")));
    }

}
