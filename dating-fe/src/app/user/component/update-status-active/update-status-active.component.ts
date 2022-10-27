import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {UpdateAvatarService} from "../../service/update-avatar.service";
import {FormControl, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../../service/authentication/token-storage.service";
import {AuthenticationService} from "../../../service/authentication/authentication.service";

@Component({
  selector: 'app-update-status-active',
  templateUrl: './update-status-active.component.html',
  styleUrls: ['./update-status-active.component.css']
})
export class UpdateStatusActiveComponent implements OnInit {

  user: User;
  updateForm: FormGroup;

  constructor(private updateAvatarService: UpdateAvatarService, private token: TokenStorageService,
              private auth: AuthenticationService) {

  }

  ngOnInit(): void {

  }

  updateActive(event) {
    this.auth.getUserByAccount(this.token.getUser().idAccount).subscribe(data => {
      this.updateForm = new FormGroup({
        idUser: new FormControl(data.idUser),
        statusActive: new FormControl(event)
      });
      this.updateAvatarService.getUpdateActive(this.updateForm.value).subscribe(next => {
      })
    })
  }
}
