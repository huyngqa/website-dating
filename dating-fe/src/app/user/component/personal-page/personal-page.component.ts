import {Component, Inject, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {Hobbit} from "../../model/hobbit";
import {HobbitService} from "../../service/hobbit.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {finalize} from "rxjs/operators";
import {AngularFireStorage} from "@angular/fire/storage";
import {PostService} from "../../service/post.service";
import {NewFeed} from "../../model/new-feed";
import {SendRequestService} from "../../../friend/friend-service/send-request.service";
import {TokenStorageService} from "../../../service/authentication/token-storage.service";
import {UserServiceService} from "../../service/user-service.service";
import { NgxUiLoaderService } from "ngx-ui-loader";
import {AuthenticationService} from "../../../service/authentication/authentication.service";
import {Report} from "../../../website/model/report";
import {ReportDetailService} from "../../../website/service/report-detail.service";
import {ToastrService} from "ngx-toastr";


@Component({
  selector: 'app-personal-page',
  templateUrl: './personal-page.component.html',
  styleUrls: ['./personal-page.component.css']
})
export class PersonalPageComponent implements OnInit {
  public loading = false;
  relationship: any;
  isOwn: boolean;
  myUser;
  myAccount
  myIdUser;
  idUser;
  user: User;
  hobbitList: Hobbit[] = [];
  userAddressArr: string[] = [];
  postCreate: FormGroup;
  fileList: any = "";
  links: any[] = [];
  submitter = false;
  listShow: NewFeed[] = [];
  loadImage: any[] = [];
  idPost
  sendReport = []
  reportDetailForm: FormGroup = new FormGroup({
    id: new FormControl(''),
    post: new FormControl(''),
    reporter: new FormControl(''),
    report: new FormControl('',[Validators.required]),
    status: new FormControl(''),
    timeReport: new FormControl('')
  });
  reportList: Report[] = [];
  today= new Date()

  constructor(private userService: UserServiceService,
              private hobbitService: HobbitService,
              private sendRequestService: SendRequestService,
              private activatedRoute: ActivatedRoute,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private postService: PostService,
              private router: Router, private token: TokenStorageService,
              private ngxService: NgxUiLoaderService,
              private auth: AuthenticationService,
              private reportDetailService: ReportDetailService,
              private toastrService:ToastrService) {
    this.activatedRoute.paramMap.subscribe((paraMap: ParamMap) => {
      this.idUser = +paraMap.get('id');
    })

    this.myAccount = this.token.getUser().idAccount;
    this.auth.getUserByAccount(this.myAccount).subscribe(data=>{
      this.myUser =  data;
      this.myIdUser = this.myUser.idUser;
      console.log(this.myIdUser)
      if (this.idUser == this.myIdUser) {
        this.isOwn = true;
        console.log('own: ' + this.isOwn)
      } else {
        this.sendRequestService.checkFriend(this.myIdUser, this.idUser).subscribe(result => {
          this.relationship = result;
        })
      }


      this.postCreate = new FormGroup({
        idPost: new FormControl(""),
        content: new FormControl("", [Validators.required]),
        media: new FormControl(""),
        user: new FormGroup({
          idUser: new FormControl(this.myIdUser)
        })
      })
    })

    this.reportDetailService.getAllReport().subscribe(data => {
      console.log(data);
      this.reportList = data;
    }, error => {
      this.router.navigateByUrl("/share/error404")
    });
  }





  ngOnInit(): void {
    this.userService.getUserById(this.idUser).subscribe((userDb) => {
      console.log(userDb)
      this.user = userDb;
      this.user.typeUser = {};
      this.user.typeUser.idTypeUser = userDb.idTypeUser;
      console.log(this.user)
      this.userAddressArr = this.user.address.split(',');
      this.user.address = this.userAddressArr[this.userAddressArr.length - 1]
    })
    this.hobbitService.getHobbitByIdUser(this.idUser).subscribe(hobbits => {
      this.hobbitList = hobbits;
    })
    this.getListPost();
  }

  getListPost() {
    this.postService.getListPost(this.idUser).subscribe(data => {
      this.listShow = data;
      for (let i = 0; i < this.listShow.length; i++) {
        this.listShow[i].mediaArr = this.listShow[i].media.split(',');
      }
    })
  }

  addRequest() {
    this.sendRequestService.addRequest(this.myIdUser, this.idUser).subscribe(() => {
      this.relationship = 51;
    }, err => {
      console.log(err)
    })
  }

  removeRequest() {
    this.sendRequestService.removeRequest(this.myIdUser, this.idUser).subscribe(() => {
      this.relationship = 0;
    }, err => {
      console.log(err)
    })
  }

  show(event: any) {
    this.links = event.target.files;
    if (event.target.files.length > 0) {
      for (let i = 0; i < event.target.files.length; i++) {
        if (event.target.files && event.target.files[i]) {
          var reader = new FileReader();
          reader.onload = (event: any) => {
            this.loadImage.push(event.target.result);
          }
          reader.readAsDataURL(event.target.files[i]);
        }
      }
    }


  }

  uploadFile(img: any) {
    return new Promise((resolve, reject) => {
      const nameImg = Date.now() + img.name;
      const fileRef = this.storage.ref(nameImg);
      this.storage.upload(nameImg, img).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe((url) => {
            this.postCreate.patchValue({img: url});
            resolve(true)
            this.fileList += url + ",";
          });
        })
      ).subscribe()
    });
  }

  async handleFiles() {
    for (let i = 0; i < this.links.length; i++) {
      await this.uploadFile(this.links[i])
    }
  }

  savePost() {
    this.ngxService.start();
    this.submitter = true;
    this.handleFiles().then(() => {
      this.postCreate.value.media = this.fileList
      this.postService.create(this.postCreate.value).subscribe(() => {
        this.getListPost();
        document.getElementById("errDiv").hidden;
      });
    }).finally(() => {
      this.postCreate.reset();
      window.location.reload();
      this.ngxService.stop();
    })
  }


  resetModal() {
    this.sendReport = [];
  }

  submitReport() {
    const reportDetail = this.reportDetailForm.value;
    console.log(reportDetail)
    if (this.reportDetailForm.valid){
      this.reportDetailService.save(reportDetail).subscribe(() => {
        this.toastrService.success("Tó cáo thành công","Thông báo")
      }, e => {
        this.router.navigateByUrl("/share/error")
      });
    }else {
      this.toastrService.warning("Bạn chưa chọn nội dung tố cáo", "Thông báo");
    }
  }

  elementReport(idPost: number) {
    this.idPost = idPost;
    console.log(idPost + "sdsassd")
    this.reportDetailForm = new FormGroup({
      id: new FormControl(''),
      post: new FormControl(this.idPost),
      reporter: new FormControl(this.myIdUser),
      report: new FormControl('',Validators.required),
      status: new FormControl(8),
      timeReport: new FormControl(this.today)
    });
  }
}
