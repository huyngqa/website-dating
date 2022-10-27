import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../service/authentication/token-storage.service";

@Component({
  selector: 'app-error-report',
  templateUrl: './error-report.component.html',
  styleUrls: ['./error-report.component.css']
})
export class ErrorReportComponent implements OnInit {

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
  }

  logOut() {
    this.tokenStorageService.logOut();
    window.location.reload();
  }
}
