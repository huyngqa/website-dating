import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class TargetService {

  constructor(private httpClient: HttpClient, private auth :AuthenticationService) { }

  get(): Observable<any>{
    return this.httpClient.get('http://localhost:8080/api/users/get-all-target',this.auth.getToken());
  }
}
