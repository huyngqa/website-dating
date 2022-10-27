import {Component, Inject, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {Hobbit} from '../../model/hobbit';
import {HobbitService} from '../../service/hobbit.service';
import {TargetService} from '../../service/target.service';
import {Target} from '../../model/target';
import {AngularFireStorage} from '@angular/fire/storage';
import {finalize} from 'rxjs/operators';
import {User} from '../../model/user';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {UserServiceService} from "../../service/user-service.service";
import {validatorAge} from "../../../utils/DateTimeUtil";
import {NgxUiLoaderService} from "ngx-ui-loader";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  imgLoad: any = '';

  imgs: any = '';
  registerUser: FormGroup;

  hobbitList: Hobbit[] = [];

  targetList: Target[] = [];

  avatarUrl: any = '';

  user: User;
  idUser: number;

  constructor(private hobbitService: HobbitService,
              private targetService: TargetService,
              private userService: UserServiceService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private ngxService: NgxUiLoaderService
  ) {
    activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.idUser = +paramMap.get('id')
      // console.log(this.idUser)
      this.registerUser = new FormGroup({
        idUser: new FormControl(this.idUser),
        avatar: new FormControl('', [Validators.required]),

        nameGroup: new FormGroup({
          firstName: new FormControl('', [Validators.required]),
          lastName: new FormControl('', [Validators.required]),
        }),

        dateOfBirth: new FormControl('', [Validators.required, validatorAge]),

        gender: new FormControl('', [Validators.required]),

        address: new FormControl('', [Validators.required]),

        job: new FormControl('', [Validators.required]),

        married: new FormControl('', [Validators.required]),

        hobbits: new FormArray([]),

        targets: new FormArray([])
      });
    })
  }


  ngOnInit(): void {
    this.hobbitService.get().subscribe(next => {
      this.hobbitList = next;
    });

    this.targetService.get().subscribe(next => {
      this.targetList = next;
    });

  }

   save() {
      this.uploadFile().then(() => {
        this.user = this.registerUser.value;
        console.log("this user", this.user)
        let firstName = this.registerUser.controls.nameGroup.get('firstName').value;
        let lastName = this.registerUser.controls.nameGroup.get('lastName').value;
        this.user.name = firstName + ' ' + lastName;
        this.user.avatar = this.avatarUrl;
      }).finally(() => {
        this.userService.update(this.idUser, this.registerUser.value).subscribe(() => {
          this.router.navigateByUrl('/user/newFeed')
        })
      })


  }

  uploadFile() {
    return new Promise((resolve, reject) => {
        const nameImg = Date.now() + this.imgs.name;
        const fileRef = this.storage.ref(nameImg);
        this.storage.upload(nameImg, this.imgs).snapshotChanges().pipe(
          finalize(() => {
            fileRef.getDownloadURL().subscribe((url) => {
              this.registerUser.patchValue({img: url});
              resolve(true);
              this.avatarUrl = url;
              console.log(this.avatarUrl)
            });
          })
        ).subscribe();
      }
    );
  }

  showAvatar($event: any) {
    this.imgs = $event.target.files[0];
    console.log(this.imgs)
    if ($event.target.files && $event.target.files[0]) {
      this.imgLoad = $event.target.files[0];
      const reader = new FileReader();
      reader.onload = e => this.imgLoad = reader.result;
      reader.readAsDataURL(this.imgLoad);
    }
  }

  onCheckBoxChange($event: any) {
    const checkArray: FormArray = this.registerUser.get('hobbits') as FormArray;
    if ($event.target.checked) {
      checkArray.push(new FormControl($event.target.value));
    } else {
      let i: number = 0;
      checkArray.controls.forEach((item: FormControl) => {
        if (item.value == $event.target.value) {
          checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  onCheckBoxTarget($event: any) {
    const checkArray: FormArray = this.registerUser.get('targets') as FormArray;
    if ($event.target.checked) {
      checkArray.push(new FormControl($event.target.value));
    } else {
      let i: number = 0;
      checkArray.controls.forEach((item: FormControl) => {
        if (item.value == $event.target.value) {
          checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }
}
