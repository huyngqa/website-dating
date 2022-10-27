import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FriendRoutingModule} from './friend-routing.module';
import {FriendlistComponent} from './component/friendlist/friendlist.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FriendRequestComponent} from "./component/friend-request/friend-request.component";
import {ShareModule} from "../share/share.module";


@NgModule({
  declarations: [FriendlistComponent, FriendRequestComponent],
  exports: [
    FriendlistComponent
  ],
    imports: [
        CommonModule,
        FriendRoutingModule,
        ReactiveFormsModule,
        FormsModule,
        ShareModule,
    ]
})
export class FriendModule {
}
