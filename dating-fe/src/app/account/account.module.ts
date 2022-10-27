import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AccountRoutingModule} from './account-routing.module';
import {LoginComponent} from './component/login/login.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AccountCreateComponent} from "./component/account-create/account-create.component";
import {ResetPasswordComponent} from "./component/reset-password/reset-password.component";
import {ShareModule} from "../share/share.module";


@NgModule({
  declarations: [LoginComponent, AccountCreateComponent, ResetPasswordComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ShareModule
  ],
  exports: [
    ResetPasswordComponent
  ]
})
export class AccountModule {
}
