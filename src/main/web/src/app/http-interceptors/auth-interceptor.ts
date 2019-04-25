import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, of, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {Token} from "../model/token";
import {AuthService} from "../services/auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private router: Router) {
    }

    private handleAuthError(err: HttpErrorResponse): Observable<any> {
        if ((err.status === 401) && (!this.router.isActive("/login", true))) {
            this.router.navigate(["/login"]);
            return of(err.message);
        }
        return throwError(err);
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let token: Token = AuthService.loadToken();
        const authReq = request.clone({
            setHeaders: {"token": token.accessToken + ":" + token.refreshToken},
            withCredentials: true
        });
        return next.handle(authReq).pipe(catchError(err => this.handleAuthError(err)))
    }
}