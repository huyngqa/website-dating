import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {SearchComponent} from "./component/search/search.component";
import {PersonalPageComponent} from "./component/personal-page/personal-page.component";
import {UpgradeAccountComponent} from "./component/upgrade-account/upgrade-account.component";
import {AuthGuardService} from "../service/authentication/auth-guard.service";
import {UpdateAvatarComponent} from "./component/update-avatar/update-avatar.component";
import {DetailPostComponent} from "./component/detail-post/detail-post.component";
import {ListUserComponent} from "./component/list-user/list-user.component";
import {CreateUserComponent} from "./component/create-user/create-user.component";
import {PostComponent} from "./component/post/post.component";
import {AuthReportService} from "../service/authentication/auth-report.service";


const routes: Routes = [{
  path: "searchFriend",
  component: SearchComponent, canActivate: [AuthGuardService, AuthReportService]

},
  {
    path: "users/:id",
    component: PersonalPageComponent,
    canActivate: [AuthGuardService, AuthReportService]
  },
  {
    path: "upgradeAccount",
    component: UpgradeAccountComponent,
    canActivate: [AuthGuardService, AuthReportService]
  },
  {
    path: "updateAvatar",
    component: UpdateAvatarComponent,
    canActivate: [AuthGuardService]
  },

  {path: "newFeed", component: PostComponent, canActivate: [AuthGuardService, AuthReportService]
  },

  {path: "user/newFeed/:id/:id1", component: DetailPostComponent, canActivate: [AuthGuardService, AuthReportService]
  },

  {path: "listUser", component: ListUserComponent, canActivate: [AuthGuardService, AuthReportService]},
  {path: "create-user/:id", component: CreateUserComponent, canActivate: [AuthGuardService, AuthReportService]},
  {path: "detailPost/:idPost", component: DetailPostComponent, canActivate: [AuthGuardService, AuthReportService]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {
}
