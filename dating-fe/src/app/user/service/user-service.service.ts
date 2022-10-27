import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {TokenStorageService} from "../../service/authentication/token-storage.service";
import {NewFeed} from "../model/new-feed";
import {environment} from "../../../environments/environment";
import {User} from "../model/user";
import {TypeUser} from "../model/type-user";
import {ReportDetail} from "../../website/model/report-detail";
import {CommentPost} from "../model/comment-post";

const SERVICE_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private countSource = new BehaviorSubject('0');
  countLength = this.countSource.asObservable();

  takeLength(count: any){
    this.countSource.next(count);
  }


  constructor(private  httpClient: HttpClient, private auth: AuthenticationService,
              private tokenStorageService: TokenStorageService) {
  }

  getAll(): Observable<any> {
    return this.httpClient.get<any>
    (`http://localhost:8080/api/users/listAndSearch`, this.auth.getToken())
  }

  getIdAccount() {

  }

  public getUserById(id: number): Observable<any> {
    return this.httpClient.get("http://localhost:8080/api/users/users/" + id, this.auth.getToken());
  }

  getSearch(name: string,
            dateOfBirth: string,
            address: string,
            job: string,
            gender: string,
            hobbitName: string): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/api/users/listAndSearch?name=${name}&address=${address}&job=${job}&dateOfBirth=${dateOfBirth}&gender=${gender}&hobbitName=${hobbitName}`,
      this.auth.getToken())
  }

  getListPost(id: number): Observable<any> {
    console.log(id)
    return this.httpClient.get<NewFeed[]>("http://localhost:8080/api/users/list/" + id, this.auth.getToken());
  }

  getNewFeed(id: number): Observable<any> {
    return this.httpClient.get<NewFeed>("http://localhost:8080/api/users/findPost/" + id, this.auth.getToken());
  }

  updatePost(newFeed: NewFeed) {
    return this.httpClient.patch("http://localhost:8080/api/users/update/" + newFeed.idUser, newFeed, this.auth.getToken());
  }

  findByAllAndSearchNameUser(name: string, typeUser: string, page: number): Observable<any> {
    return this.httpClient.get<User[]>(SERVICE_URL + "/api/admin/list/user?name=" + name + "&typeUser=" + typeUser + "&page=" + page, this.auth.getToken());
  }

  getAllTypeUser(): Observable<any> {
    return this.httpClient.get<TypeUser[]>(SERVICE_URL + "/api/admin/list/typeUser", this.auth.getToken());
  }

  getAllReportDetail(id: number): Observable<any> {
    return this.httpClient.get<ReportDetail[]>(`${SERVICE_URL}/api/admin/list/warning/${id}`, this.auth.getToken());
  }

  updateStatusWarrningUser(request: any): Observable<any> {
    return this.httpClient.patch<any>(`${SERVICE_URL}/api/admin/update/status`, request, this.auth.getToken());
  }

  findByIdUser(id: number): Observable<any> {
    return this.httpClient.get(`${SERVICE_URL}/api/admin/findId/${id}`, this.auth.getToken());
  }

  update(id: number, user: User) {
    return this.httpClient.patch(`http://localhost:8080/api/users/update-account/${id}`, user, this.auth.getToken());
  }
  getComment(id:number):Observable<any>{
    return this.httpClient.get<CommentPost[]>('http://localhost:8080/api/users/display_comment/'+id,this.auth.getToken() )
  }

  addComment(comment : Comment){
    console.log(comment)
    return this.httpClient.post('http://localhost:8080/api/users/add_comment',comment,this.auth.getToken())
  }
}
