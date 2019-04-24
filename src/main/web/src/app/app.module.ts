import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {ReasonGroupsComponent} from './reason-groups/reason-groups.component';
import {httpInterceptorProviders} from "./http-interceptors";
import {Router, RouterModule} from "@angular/router";

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ReasonGroupsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
    ],
    providers: [httpInterceptorProviders],
    bootstrap: [AppComponent]
})
export class AppModule {
}
