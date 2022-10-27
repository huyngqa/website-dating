import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {UserService} from "../service/user.service";
import {User} from "../../user/model/user";
import {TokenStorageService} from "../../service/authentication/token-storage.service";
import {UserServiceService} from "../../user/service/user-service.service";
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output()keySearch = new EventEmitter();
  user :User[] =[];
  myUser: User;
  myIdUser;
  name:string;
  roleList =[]
  constructor(private userService : UserService,
              private tokenStorageService: TokenStorageService
              , private userServiceService:UserServiceService,
              private auth: AuthenticationService,
              private router: Router
              ) {

  }
  ngOnInit(): void {
    if (this.tokenStorageService.getUser()){
      this.myIdUser = this.tokenStorageService.getUser().idAccount;
      this.auth.getUserByAccount(this.myIdUser).subscribe(user=>{
        this.myUser = user;
        this.myIdUser = user.idUser;
        console.log(this.myUser.avatar)
      })
    }
   this.roleList = this.tokenStorageService.getUser().roles
  }

  logOut() {
    this.tokenStorageService.logOut();
    window.location.reload();
  }

  reload() {
    this.router.navigate(['/user/users',this.myIdUser]).then(n=>{
      window.location.reload()
    })

  }

  load() {
    this.router.navigate(['/share/list',this.name]).then(n=>{
      window.location.reload()
    })
  }
}
