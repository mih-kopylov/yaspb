import {Component, OnInit} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {isDefined} from "@angular/compiler/src/util";

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
    model = new LoginForm();

    constructor(
        private authService: AuthService,
        private router: Router,
    ) {
    }


    ngOnInit() {
        if (isDefined(AuthService.loadToken())) {
            this.router.navigate(["/"]);
        }
    }

    doLogin() {
        this.authService.login(this.model.login, this.model.password).subscribe(() => {
            this.router.navigate(["/"]);
        });
    }
}


class LoginForm {
    login: string;
    password: string;
}