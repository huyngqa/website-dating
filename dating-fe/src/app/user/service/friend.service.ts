import { Injectable } from '@angular/core';
import {User} from "../model/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  constructor(private httpClient:HttpClient, private auth: AuthenticationService) { }

  getSuggestRequest(idSuggest: number, gender:boolean): Observable<any> {
    return this.httpClient.get<User[]>(`http://localhost:8080/api/users/suggest/${idSuggest}/${gender}`,
      this.auth.getToken());
  }

}
