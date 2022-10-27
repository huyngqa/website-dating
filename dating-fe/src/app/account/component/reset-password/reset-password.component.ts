import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ResetPasswordService} from "../../service/reset-password.service";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../../service/authentication/authentication.service";
import {TokenStorageService} from "../../../service/authentication/token-storage.service";


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  jwtRequestForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private resetPasswordService: ResetPasswordService,
              private toastr: ToastrService,
              private tokens: TokenStorageService,
              private auth: AuthenticationService,
              private router: Router,
              private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute) {
    this.jwtRequestForm = new FormGroup({
      newPassword: new FormControl(''),
      password: new FormControl(''),
      // verificationCode: new FormControl(''),
    })
  }

  validationMessages = {
    password: [
      {type: 'required', message: 'Trường này không được để trống!'},
      {type: 'minlength', message: 'Mật khẩu cần nhiều hơn 8 ký tự'},
      {type: 'maxlength', message: 'Mật khẩu chỉ được ít hơn 32 ký tự'},
    ]
  };

  ngOnInit(): void {

  }

  onSubmit() {
    console.log(this.tokens.getUser().idAccount)
    this.resetPasswordService.doResetPassword(this.jwtRequestForm.value, this.tokens.getUser().idAccount).subscribe(() => {
      this.jwtRequestForm.reset();
      this.toastr.success('Đổi mật khẩu thành công', 'Thông báo');
    }, () => {
      this.toastr.error('Đổi mật khẩu thất bại', 'Thông báo');
    });
  }
}
