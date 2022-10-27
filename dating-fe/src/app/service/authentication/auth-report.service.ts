import {Injectable} from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {TokenStorageService} from "./token-storage.service";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthReportService implements CanActivate {

  constructor(private tokens: TokenStorageService,
              private auth: AuthenticationService,
              private router: Router) {
  }

  canActivate(): boolean {
    let email: string = this.tokens.getUser().email;
    let statusReport: number;
    console.log(email)
    this.auth.getUserByEmail(email).subscribe(data => {
      statusReport = data.status;
      console.log(statusReport)
      if (!(statusReport < 14 || statusReport > 16)) {
        console.log(statusReport < 14 || statusReport > 16)
        this.router.navigate(['/share/error-report']);
        return false;
      }
    });
    return true
  }
}
