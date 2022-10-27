import {Component, Inject, Input, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {NewFeed} from "../../model/new-feed";
import {UserServiceService} from "../../service/user-service.service";
import {FriendService} from "../../service/friend.service";
import {FriendListService} from "../../../friend/friend-service/friend-list.service";
import {ToastrService} from "ngx-toastr";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../../../service/authentication/token-storage.service";
import {AuthenticationService} from "../../../service/authentication/authentication.service";
import firebase from "firebase/app";
import {AngularFirestore} from '@angular/fire/firestore';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../../service/post.service";
import {AngularFireStorage} from "@angular/fire/storage";
import {finalize} from "rxjs/operators";
import {NgxUiLoaderService} from "ngx-ui-loader";
import {Report} from "../../../website/model/report";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ReportDetailService} from "../../../website/service/report-detail.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  suggestList: User[];
  listShow: NewFeed[] = [];
  groups: any[] = [];
  user: any;
  input: string = '';
  flag = true
  userId;
  idAcc: any;
  loadImage: any[] = [];
  fileList: any = "";
  links: any[] = [];
  postCreate: FormGroup;
  myIdUser;
  lengthComment: number;
  idPost: number;
  sendReport = [];
  newUse: User

  reportDetailForm: FormGroup = new FormGroup({
    id: new FormControl(''),
    post: new FormControl(''),
    reporter: new FormControl(''),
    report: new FormControl('', [Validators.required]),
    status: new FormControl(''),
    timeReport: new FormControl('')
  });
  reportList: Report[] = [];
  today = new Date()

  constructor(private service: UserServiceService,
              private friendService: FriendService,
              private friendListService: FriendListService,
              private toast: ToastrService, private http: HttpClient,
              private tokens: TokenStorageService,
              private auth: AuthenticationService,
              public database: AngularFirestore,
              private postService: PostService,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private ngxService: NgxUiLoaderService,
              private reportDetailService: ReportDetailService,
              private router: Router,
  ) {
    this.auth.getUserByAccount(this.tokens.getUser().idAccount).subscribe(data => {
      this.newUse = data;
    }, () => {
    }, () => {
      // this.service.getComment(this.idAcc.idPost).subscribe(data=>{
      //   this.lengthComment = data;
      // })
      this.service.getListPost(this.newUse.idUser).subscribe(async data => {
        console.log(data)
        if (data != null) {
          this.listShow = data;

        for (let i = 0; i < this.listShow.length; i++) {
          this.listShow[i].mediaArr = this.listShow[i].media.split(",")
          this.listShow[i].arrContent = this.listShow[i].content.split(' ')
          if (this.listShow[i].mediaArr.length > 1) {
            this.listShow[i].mediaArr.pop()
          }
        }
        console.log(this.listShow)
        }
      })
      this.postCreate = new FormGroup({
        idPost: new FormControl(""),
        content: new FormControl(""),
        media: new FormControl(""),
        user: new FormGroup({
          idUser: new FormControl(this.newUse.idUser)
        })
      })

      // thai suggestList
      this.friendService.getSuggestRequest(this.newUse.idUser, this.newUse.gender).subscribe(suggest => {
        console.log(suggest)
        this.suggestList = suggest;
      })

      // end thai

    });

    this.reportDetailService.getAllReport().subscribe(data => {
      console.log(data);
      this.reportList = data;
    }, error => {
      this.router.navigateByUrl("/share/error404")
    });
  }

  ngOnInit(): void {
  }


  /*add(myId: any, idUser: number) {
    this.suggestService.addRequest(myId, idUser)
  }*/
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
    console.log(this.links)
    this.handleFiles().then(() => {
      this.postCreate.value.media = this.fileList
      // console.log(this.postCreate.value.media)
      console.log(this.postCreate.value)
      this.postService.create(this.postCreate.value).subscribe(() => {
        this.postCreate.reset();
        document.getElementById("errDiv").hidden;

      });
    }).finally(() => {
      window.location.reload();
      this.ngxService.stop()
    })
  }

  elementReport(idPost: number) {
    this.idPost = idPost;
    console.log(idPost + "sdsassd")
    this.reportDetailForm = new FormGroup({
      id: new FormControl(''),
      post: new FormControl(this.idPost),
      reporter: new FormControl(this.newUse.idUser),
      report: new FormControl('', Validators.required),
      status: new FormControl(8),
      timeReport: new FormControl(this.today)
    });
  }

  submitReport() {
    const reportDetail = this.reportDetailForm.value;
    console.log(reportDetail)
    if (this.reportDetailForm.valid) {
      this.reportDetailService.save(reportDetail).subscribe(() => {
        this.toast.success("Tó cáo thành công", "Thông báo")
      }, e => {
        this.router.navigateByUrl("/share/error")
      });
    } else {
      this.toast.warning("Bạn chưa chọn nội dung tố cáo", "Thông báo");
    }
  }

  resetModal() {
    this.sendReport = [];
  }


}
