import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WebsiteRoutingModule } from './website-routing.module';
import { CreateReportDetailComponent } from './component/create-report-detail/create-report-detail.component';
import { ReportComponent } from './component/report/report.component';
import { ReportDetailComponent } from './component/report-detail/report-detail.component';
import {ReactiveFormsModule} from "@angular/forms";
import {ShareModule} from "../share/share.module";

@NgModule({
  declarations: [CreateReportDetailComponent, ReportComponent, ReportDetailComponent],
    imports: [
        CommonModule,
        WebsiteRoutingModule,
        ReactiveFormsModule,
        ShareModule
    ]
})
export class WebsiteModule { }
