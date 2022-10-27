import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SearchComponent} from "./component/search/search.component";
import {PersonalPageComponent} from './component/personal-page/personal-page.component';
import {UpgradeAccountComponent} from './component/upgrade-account/upgrade-account.component';
import {UpdateAvatarComponent} from './component/update-avatar/update-avatar.component';
import {WebcamModule} from "ngx-webcam";
import {UpdateStatusActiveComponent} from './component/update-status-active/update-status-active.component';
import {ShareModule} from "../share/share.module";
import {CreateUserComponent} from "./component/create-user/create-user.component";
import {DetailPostComponent} from "./component/detail-post/detail-post.component";
import {ListUserComponent} from "./component/list-user/list-user.component";
import {PostComponent} from "./component/post/post.component";
import {MatMenuModule} from "@angular/material/menu";
import {AccountModule} from "../account/account.module";


@NgModule({
  declarations: [SearchComponent,PersonalPageComponent,UpgradeAccountComponent,
    UpdateAvatarComponent, UpdateStatusActiveComponent,
  CreateUserComponent,DetailPostComponent,ListUserComponent,PostComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    WebcamModule,
    ShareModule,
    MatMenuModule,
    AccountModule,
  ],

  exports: []
})
export class UserModule {
}
