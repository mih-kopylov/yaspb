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

    private static handleAuthError(err: HttpErrorResponse): Observable<any> {
        if ((err.status === 401) && (location.pathname !== "/login")) {
            location.href = "/login";
            // this.router.navigateByUrl("/login");
            // if you've caught / handled the error, you don't want to rethrow it unless you also want downstream
            // consumers to have to handle it as well.
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
        return next.handle(authReq).pipe(catchError(AuthInterceptor.handleAuthError))
    }
}