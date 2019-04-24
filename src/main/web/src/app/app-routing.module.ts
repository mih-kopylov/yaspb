import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {ReasonGroupsComponent} from "./reason-groups/reason-groups.component";

const routes: Routes = [
    {
        path: "",
        component: ReasonGroupsComponent,
    },
    {
        path: "login",
        component: LoginComponent,
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
