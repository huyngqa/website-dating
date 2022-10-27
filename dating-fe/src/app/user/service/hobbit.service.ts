import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class HobbitService {

  constructor(private http: HttpClient, private auth: AuthenticationService) {
  }

  public getHobbitByIdUser(id: number): Observable<any> {
    return this.http.get("http://localhost:8080/api/users/hobbits/" + id, this.auth.getToken());
  }
  get(): Observable<any>{
    return this.http.get('http://localhost:8080/api/users/get-all-hobbit', this.auth.getToken());
  }
}
