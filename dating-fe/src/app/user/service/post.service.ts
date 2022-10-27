import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {NewFeed} from "../model/new-feed";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient, private auth: AuthenticationService) { }

  create(value: any) {
    return this.httpClient.post("http://localhost:8080/api/users/posts/save",value, this.auth.getToken());
  }

  getListPost(id : number):Observable<any>{
    return this.httpClient.get<NewFeed[]>("http://localhost:8080/api/users/posts/personList/"+id, this.auth.getToken());
  }
  getNewFeed(id: number):Observable<any>{
    return this.httpClient.get<NewFeed>("http://localhost:8080/api/users/findPost/"+id);
  }
  updatePost(newFeed:NewFeed){
    return this.httpClient.patch("http://localhost:8080/api/users/update/"+ newFeed.idUser,newFeed);
  }
}
