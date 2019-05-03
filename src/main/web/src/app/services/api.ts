import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class Api {
    private static BASE_URL: string = environment.baseUrl + "/rest";
    public static LOGIN: string = Api.BASE_URL + "/auth/login";
    public static PROFILE: string = Api.BASE_URL + "/profile";
    public static REASON_GROUPS: string = Api.BASE_URL + "/reasonGroups";
    public static PROBLEM: string = Api.BASE_URL + "/problems";
}
