import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  httpOptions: any;
  isLoggedIn: boolean;

  constructor(private httpClient: HttpClient, private tokenStorageService: TokenStorageService) {

  }

  getToken() {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + this.tokenStorageService.getToken()
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
    return this.httpOptions;
  }

  login(username: string, password: string): Observable<any> {
    return this.httpClient.post("http://localhost:8080/api/public/login",
      {username: username, password: password});
  }

  public isAuthenticated(): boolean {
    // const jwtToken = this.tokenStorageService.getToken();
    const tokenSession = sessionStorage.getItem('auth-token');
    const tokenLocal = localStorage.getItem('auth-token');
    // Check whether the token is expired and return
    // true or false
    if (tokenLocal != null || tokenSession != null) {
      return true;
    }
    return false;
  }


  getUserByAccount(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/api/users/my-user/${id}`, this.getToken());
  }

  getUserByEmail(email: string): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/api/users/my-account/${email}`, this.getToken());
  }
}
