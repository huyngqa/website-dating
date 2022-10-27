import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {JwtRequest} from "../model/jwt-request";
import {AuthenticationService} from "../../service/authentication/authentication.service";

const AUTH_API = 'http://localhost:8080/api/users/account';

@Injectable({
  providedIn: 'root'
})
export class ResetPasswordService {
  constructor(private http: HttpClient, private auth: AuthenticationService) {
  }

  doResetPassword(authenticationRequest: JwtRequest, idAccount: number): Observable<any> {
    return this.http.post<JwtRequest>(`${AUTH_API}/changePassword/${idAccount}`, authenticationRequest, this.auth.getToken());
  }
}
