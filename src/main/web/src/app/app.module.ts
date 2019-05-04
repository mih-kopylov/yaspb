import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {HttpClientModule} from "@angular/common/http";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./login/login.component";
import {ReasonGroupsComponent} from "./reason-groups/reason-groups.component";
import {httpInterceptorProviders} from "./http-interceptors";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
    MatAutocompleteModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatSnackBarModule,
    MatTooltipModule,
} from "@angular/material";
import {FlexModule} from "@angular/flex-layout";
import {CreateProblemComponent} from "./create-problem/create-problem.component";
import {ProfileComponent} from "./profile/profile.component";
import {CreateReasonGroupComponent} from "./create-reason-group/create-reason-group.component";

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ReasonGroupsComponent,
        CreateProblemComponent,
        ProfileComponent,
        CreateReasonGroupComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        FlexModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        MatSnackBarModule,
        MatIconModule,
        MatAutocompleteModule,
        MatTooltipModule,
        MatSelectModule,
    ],
    providers: [httpInterceptorProviders],
    bootstrap: [AppComponent],
})
export class AppModule {
}
