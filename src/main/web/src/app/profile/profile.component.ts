import {Component, OnInit} from "@angular/core";
import {ProfileService} from "../services/profile.service";
import {Profile} from "../model/profile";
import {isDefined} from "@angular/compiler/src/util";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: "app-profile",
    templateUrl: "./profile.component.html",
    styleUrls: ["./profile.component.css"],
})
export class ProfileComponent implements OnInit {
    private profile: Profile;

    constructor(
        private profileService: ProfileService,
        private authService: AuthService,
        private router: Router) {
    }

    ngOnInit() {
        this.updateProfile();
        this.authService.authEvents.subscribe(() => this.updateProfile());
    }


    isLoggedIn() {
        return isDefined(this.profile);
    }

    getProfileName() {
        return this.profile.first_name + " " + this.profile.last_name + " (" + this.profile.email + ")";
    }

    logout() {
        this.authService.logout();
        this.profile = undefined;
        this.router.navigate(["/login"]);
    }

    private updateProfile() {
        this.profileService.getProfile().subscribe(profile => this.profile = profile);
    }
}
