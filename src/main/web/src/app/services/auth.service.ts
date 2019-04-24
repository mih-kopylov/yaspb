import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

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
        return this.httpClient.post("http://localhost:8080/rest/auth/login", body).pipe(response => {
            console.log(response);
            return response;
        });
    }
}
