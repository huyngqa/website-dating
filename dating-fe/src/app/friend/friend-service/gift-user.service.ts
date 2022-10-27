import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../user/model/user";
import {Gift} from "../model/gift";
import {GiftUser} from "../model/gift-user";
import {AuthenticationService} from "../../service/authentication/authentication.service";

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class GiftUserService {

  constructor(private http: HttpClient, public auth: AuthenticationService) {

  }

  giveAGiftUser(idGift: number, idUserSender : number, idUserReceiver : number, quantity: number): Observable<any> {
    return this.http.get<GiftUser>(`${API_URL}/api/users/gift/saveGiftUser?idGift=${idGift}&idUserSender=${idUserSender}&idUserReceiver=${idUserReceiver}&quantity=${quantity}`,this.auth.getToken());
  }

  findByIdUser(idUser: number): Observable<any> {
    return this.http.get<User>(`${API_URL}/api/users/user/${idUser}`,this.auth.getToken());
  }

  findByIdGift(idGift: number): Observable<any> {
    return this.http.get<Gift>(`${API_URL}/api/users/gift/${idGift}`,this.auth.getToken());
  }

}
