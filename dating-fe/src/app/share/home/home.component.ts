import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";
import {User} from "../../user/model/user";
import {TokenStorageService} from "../../service/authentication/token-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: User[] = [];
  name: string;
  @Output()keySearch = new EventEmitter();
  checkLogin: boolean = false;
  constructor(private userService: UserService,private router :Router, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.isLogin()
  }

  isLogin() {
    if(this.token.getToken() != null) {
      this.router.navigateByUrl('/user/newFeed');
    }
  }

  searchInHome(value: any) {
    this.router.navigate(['/share/list/', value])
  }
}
