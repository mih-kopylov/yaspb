import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {ReasonGroupsComponent} from './reason-groups/reason-groups.component';
import {httpInterceptorProviders} from "./http-interceptors";
import {FormsModule} from "@angular/forms";

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
        FormsModule,
    ],
    providers: [httpInterceptorProviders],
    bootstrap: [AppComponent]
})
export class AppModule {
}
