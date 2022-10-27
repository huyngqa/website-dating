import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private httpClient: HttpClient, public auth: AuthenticationService) {

  }

  getAll(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/users/test', this.auth.getToken())
  }

  getAllAdmin(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/admin/test', this.auth.getToken())
  }
}
