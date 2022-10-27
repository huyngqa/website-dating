import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ListSearchComponent} from "./list-search/list-search.component";
import {Error404Component} from "./error404/error404.component";
import {HomeComponent} from "./home/home.component";
import {ErrorReportComponent} from "./error-report/error-report.component";
import {AuthGuardService} from "../service/authentication/auth-guard.service";


const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "list/:search",
    component: ListSearchComponent
  },

  {
    path: "error",
    component: Error404Component
  },
  {
    path: "error-report",
    component: ErrorReportComponent, canActivate: [AuthGuardService]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShareRoutingModule {
}
