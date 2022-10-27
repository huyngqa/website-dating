import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../user/model/user";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  constructor(private httpClient: HttpClient,public auth: AuthenticationService) {
  }
  public getRequest(id : number):Observable<any>{
    return this.httpClient.get<User[]>("http://localhost:8080/api/users/request/"+id,this.auth.getToken())
  }
  public accept(id1:number , id2:number){
    return this.httpClient.patch(`http://localhost:8080/api/users/accept/${id1}/${id2}`,'',this.auth.getToken())
  }
  public denied(id1:number , id2:number){
    return this.httpClient.patch(`http://localhost:8080/api/users/denied/${id1}/${id2}`,'',this.auth.getToken())
  }
  public addRequest(id1:number , id2:number){
    return this.httpClient.post(`http://localhost:8080/api/users/accept/${id1}/${id2}`,'',this.auth.getToken())
  }
}
