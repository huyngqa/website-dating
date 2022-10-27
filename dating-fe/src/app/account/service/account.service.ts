import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Account} from '../model/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient: HttpClient) { }

  save(account: Account){
    return this.httpClient.post('http://localhost:8080/api/public/account/save', account);
  }
}
