import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ReportDetailComponent} from "./component/report-detail/report-detail.component";
import {CreateReportDetailComponent} from "./component/create-report-detail/create-report-detail.component";
import {AuthGuardService} from "../service/authentication/auth-guard.service";
import {AuthReportService} from "../service/authentication/auth-report.service";


const routes: Routes = [
  {
    path: 'report-detail',
    component: ReportDetailComponent, canActivate: [AuthGuardService, AuthReportService]
  },
  {
    path: 'report',
    component: CreateReportDetailComponent, canActivate: [AuthGuardService, AuthReportService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WebsiteRoutingModule { }
