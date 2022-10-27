import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ReadMessageComponent} from "./component/read-message/read-message.component";
import {AuthGuardService} from "../service/authentication/auth-guard.service";
import {AuthReportService} from "../service/authentication/auth-report.service";


const routes: Routes = [
  {
    path:"message",
    component:ReadMessageComponent, canActivate: [AuthGuardService, AuthReportService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatRoutingModule { }
