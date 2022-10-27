import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {Account} from '../../model/account';
import {AccountService} from '../../service/account.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit {

  registerAccount: FormGroup;

  account: Account;

  errorList: any;

  constructor(private accountService: AccountService,
              private router: Router) {
    this.registerAccount = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.pattern(/^[\w-\.]+@(gmail\.)+(com)$/)]),

      phone: new FormControl('', [Validators.required, Validators.pattern(/^(090|093|097)\d{7}$/)]),

      passwordGroup: new FormGroup({
        password: new FormControl('', [Validators.required, Validators.minLength(6)]),

        passwordConfirm: new FormControl()
      }, [this.checkPassword]),
    })
  }

  ngOnInit(): void {
  }

  save() {
    if (this.registerAccount.valid){
      let account: Account = this.registerAccount.value
      account.password = this.registerAccount.controls.passwordGroup.get('password').value;
      this.accountService.save(account).subscribe(next => {
        this.router.navigateByUrl("/account/login")
      },error => {
        this.errorList = error.error
      })
    }
  }

  private checkPassword(passwordGroup: AbstractControl) {

    let password = passwordGroup.get('password').value;

    let passwordConfirm = passwordGroup.get('passwordConfirm').value;

    if (password !== passwordConfirm) {
      return {'checkPasswordConfirm': true};
    }
    return null;
  }

}
