import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'account',
    loadChildren: () => import('../app/account/account.module').then(module => module.AccountModule)
  },

  {
    path: 'share',
    loadChildren: () => import('../app/share/share.module').then(module => module.ShareModule)
  },
  {
    path: 'friend',
    loadChildren: () => import('../app/friend/friend.module').then(module => module.FriendModule)
  },
  {
    path: 'user',
    loadChildren: () => import('../app/user/user.module').then(module => module.UserModule)
  },
  {
    path: 'website',
    loadChildren: () => import('../app/website/website.module').then(module => module.WebsiteModule)
  }, {
    path: 'chat',
    loadChildren: () => import('../app/chat/chat.module').then(module => module.ChatModule)
  },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


