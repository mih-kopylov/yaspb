import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {ReasonGroupsComponent} from "./reason-groups/reason-groups.component";
import {CreateProblemComponent} from "./create-problem/create-problem.component";
import {CreateReasonGroupComponent} from "./create-reason-group/create-reason-group.component";

const routes: Routes = [
    {
        path: "",
        component: ReasonGroupsComponent,
    },
    {
        path: "login",
        component: LoginComponent,
    },
    {
        path: "createProblem",
        component: CreateProblemComponent,
    },
    {
        path: "reasonGroup",
        component: CreateReasonGroupComponent,
    },
    {
        path: "reasonGroup/:id",
        component: CreateReasonGroupComponent,
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {
}
