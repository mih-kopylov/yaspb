import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model = new LoginForm();

    constructor(
        private authService: AuthService,
    ) {
    }


    ngOnInit() {
    }

    doLogin() {
        this.authService.login(this.model.login, this.model.password).subscribe(() => location.pathname = "/");
    }
}


class LoginForm {
    login: string;
    password: string;
}