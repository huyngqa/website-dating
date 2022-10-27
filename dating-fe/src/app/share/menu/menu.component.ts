import { Component, OnInit } from '@angular/core';
import {User} from "../../user/model/user";
import {NewFeed} from "../../user/model/new-feed";
import {UserServiceService} from "../../user/service/user-service.service";
import {FriendService} from "../../user/service/friend.service";
import {SuggestService} from "../../friend/friend-service/suggest.service";
import {FriendListService} from "../../friend/friend-service/friend-list.service";
import {ToastrService} from "ngx-toastr";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../../service/authentication/token-storage.service";
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {AngularFirestore} from "@angular/fire/firestore";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  groups: any[] = [];
  user: any;
  input: string = '';
  flag = true
  userId
  constructor(private service: UserServiceService,
              private friendService: FriendService,
              private suggestService : SuggestService ,
              private friendListService: FriendListService,
              private toast: ToastrService,private http: HttpClient,
              private tokens: TokenStorageService,
              private auth: AuthenticationService,
              public database: AngularFirestore,
  ) {

  }
  ngOnInit(): void {
    this.http.get<User>('http://localhost:8080/api/users/my-user/' + this.tokens.getUser().idAccount, this.auth.getToken()).subscribe(n => {
      this.user = n
      this.userId= this.user.idUser

    })

  }

  add(myId: any, idUser: number) {
    this.suggestService.addRequest(myId, idUser)
  }
}
