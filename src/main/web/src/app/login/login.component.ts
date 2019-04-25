import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model = new LoginForm();

    constructor(
        private authService: AuthService,
        private router: Router
    ) {
    }


    ngOnInit() {
    }

    doLogin() {
        this.authService.login(this.model.login, this.model.password).subscribe(() => {
            this.router.navigate(["/"])
        });
    }
}


class LoginForm {
    login: string;
    password: string;
}