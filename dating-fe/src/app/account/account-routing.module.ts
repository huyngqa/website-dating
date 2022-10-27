import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./component/login/login.component";
import {AuthGuardService} from "../service/authentication/auth-guard.service";
import {AccountCreateComponent} from "./component/account-create/account-create.component";
import {ResetPasswordComponent} from "./component/reset-password/reset-password.component";
import {AuthReportService} from "../service/authentication/auth-report.service";


const routes: Routes = [
  {
    path: "login", component: LoginComponent
  },
  {
    path: "register",
    component: AccountCreateComponent

  },
  {
    path: "resetPassword",
    component: ResetPasswordComponent, canActivate: [AuthGuardService, AuthReportService]

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
