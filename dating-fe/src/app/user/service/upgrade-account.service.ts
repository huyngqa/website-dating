import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class UpgradeAccountService {

  constructor(private httpClient: HttpClient, private auth: AuthenticationService) { }


  findByUserById(idAccount: number): Observable<any>{
    return this.httpClient.get("http://localhost:8080/api/users/upgradeAccount/detailUser/" + idAccount, this.auth.getToken());
  }


  saveInvoice(value: any) {
    return this.httpClient.post("http://localhost:8080/api/users/upgradeAccount/save",value, this.auth.getToken());
  }
}
