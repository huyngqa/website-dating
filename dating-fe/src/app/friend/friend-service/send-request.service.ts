import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class SendRequestService {

  constructor(private http: HttpClient, private auth: AuthenticationService) {
  }

  public checkFriend(idUser1, idUser2) {
    return this.http.get(`http://localhost:8080/api/users/friendList/check/${idUser1}/${idUser2}`,
      this.auth.getToken());
  }

  public addRequest(idUser1, idUser2) {
    return this.http.post(`http://localhost:8080/api/users/friendList/addRequest/${idUser1}/${idUser2}`, "",
      this.auth.getToken())
  }

  public removeRequest(idUser1, idUser2) {
    return this.http.delete(`http://localhost:8080/api/users/friendList/removeRequest/${idUser1}/${idUser2}`,
      this.auth.getToken())
  }
}
