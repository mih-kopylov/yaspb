import {Injectable} from "@angular/core";
import {
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
    HttpResponse,
} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, of, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {Token} from "../model/token";
import {AuthService} from "../services/auth.service";
import {isDefined} from "@angular/compiler/src/util";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(
        private router: Router,
        private authService: AuthService) {
    }

    private static storeTokenFromResponse(event: HttpEvent<any>) {
        if (event instanceof HttpResponse) {
            let newToken = event.headers.get("token");
            if (newToken) {
                AuthService.saveToken(Token.parse(newToken));
            }
        }
        return event;
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let token: Token = AuthService.loadToken();
        const authRequest = isDefined(token) ? request.clone({
            setHeaders: {"token": token.toHeader()},
            withCredentials: true,
        }) : request;
        return next.handle(authRequest).pipe(
            map(event => AuthInterceptor.storeTokenFromResponse(event)),
            catchError(err => this.handleAuthError(err)));
    }

    private handleAuthError(err: HttpErrorResponse): Observable<any> {
        if ((err.status === 401) && (!this.router.isActive("/login", true))) {
            this.authService.logout();
            this.router.navigate(["/login"]);
            return of(err.message);
        }
        return throwError(err);
    }
}