import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Profile} from "../model/profile";

@Injectable({
    providedIn: 'root'
})
export class ProfileService {

    constructor(
        private httpClient: HttpClient
    ) {
    }

    getProfile(): Observable<Profile> {
        return this.httpClient.get<Profile>("http://localhost:8080/rest/profile");
    }

}
