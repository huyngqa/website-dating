import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Gift} from "../model/gift";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../service/authentication/authentication.service";

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class GiftService {

  constructor(private http: HttpClient, public auth: AuthenticationService) {
  }

  getAllGift(): Observable<any> {
    return this.http.get<Gift[]>(API_URL + '/api/users/gift/listGift',this.auth.getToken())
  }

}
