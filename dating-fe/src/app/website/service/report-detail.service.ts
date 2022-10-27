import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReportDetail} from "../model/report-detail";
import {Report} from "../model/report";
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class ReportDetailService {
  private URL_CONNECT = 'http://localhost:8080/api';
  constructor(private httpClient:HttpClient,private auth: AuthenticationService){
  }

  getAllReportDetail(page: number):Observable<any>{
    return this.httpClient.get<ReportDetail[]>(this.URL_CONNECT + '/admin/report-detail' + '?page=' +page, this.auth.getToken());
  }

  getAllReport():Observable<any>{
    return this.httpClient.get<Report[]>(this.URL_CONNECT + '/users/report-list', this.auth.getToken())
  }

  findReportDetailById(id: number): Observable<any> {
    return this.httpClient.get<ReportDetail>(this.URL_CONNECT + '/admin/detail/' + id, this.auth.getToken());
  }

  confirmReportDetail(id: number): Observable<any> {
    // @ts-ignore
    return this.httpClient.patch<ReportDetail>(this.URL_CONNECT + '/admin/confirm/' + id, '', this.auth.getToken());
  }

  deleteReportDetail(id: number): Observable<any> {
    // @ts-ignore
    return this.httpClient.patch<ReportDetail>(this.URL_CONNECT + '/admin/delete/' + id,'', this.auth.getToken());
  }


  searchByKeyWord(keyWord: string,page: number): Observable<any> {
    if(keyWord==null) {
      keyWord =""
    }
    return this.httpClient.get<ReportDetail[]>(this.URL_CONNECT + '/admin/report-detail'+'?keyWord=' + keyWord + '&page='+page, this.auth.getToken());
  }

  save(reportDetail: ReportDetail) {
    return  this.httpClient.post(this.URL_CONNECT + '/users/report', reportDetail, this.auth.getToken());
  }
}
