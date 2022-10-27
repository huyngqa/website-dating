import {Component, Input, OnInit} from '@angular/core';
import {NewFeed} from "../../model/new-feed";
import {FormControl, FormGroup} from "@angular/forms";
import {finalize} from "rxjs/operators";
import {AngularFireStorage} from "@angular/fire/storage";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {UserServiceService} from "../../service/user-service.service";
import {AuthenticationService} from "../../../service/authentication/authentication.service";
import {TokenStorageService} from "../../../service/authentication/token-storage.service";
import {CommentPost} from "../../model/comment-post";
import {NgxUiLoaderService} from "ngx-ui-loader";
import {User} from "../../model/user";
import {FriendService} from "../../service/friend.service";


@Component({
    selector: 'app-detail-post',
    templateUrl: './detail-post.component.html',
     styleUrls: ['./detail-post.component.css']
})

export class DetailPostComponent implements OnInit {
  details: NewFeed = null;
  img: string[];
  arrayImg: any[] = [];
  linkUp: string = '';
  id: number;
  idCheck: number
  idAcc: any
  formUpdate: FormGroup;
  modalInfo: NewFeed = {};
  createForm: FormGroup;
  listComment: CommentPost[]
  length: number;
  element: string;
  loadArr: any[] = [];



  constructor(private service: UserServiceService,
              private storage: AngularFireStorage, private router: Router,
              private activatedRoute: ActivatedRoute, private auth: AuthenticationService,
              private tokens: TokenStorageService,
              private ngxUiLoaderService: NgxUiLoaderService,
              private friendService: FriendService,
  ) {


    this.activatedRoute.paramMap.subscribe((param: ParamMap) => {
      this.id = +param.get("idPost")
      console.log(this.id)
    })
    this.getCommentCreate();
    this.auth.getUserByAccount(this.tokens.getUser().idAccount).subscribe(data => {
      this.idAcc = data;
      this.idCheck = this.idAcc.idUser
      this.callForm()
    })
  }

  ngOnInit(): void {
    // this.friendService.getSuggestRequest(this.idCheck,this.idAcc.gender).subscribe(suggest=>{
    //   this.suggestList = suggest;
    // })
    this.service.getNewFeed(this.id).subscribe(data => {
      this.details = data;
      console.log(this.details.idUser)
      this.img = this.details.media.split(",")
    }, () => {
    }, () => {
      this.formUpdate = new FormGroup({
        idPost: new FormControl(this.details.idPost),
        content: new FormControl(this.details.content),
        idUser: new FormControl(this.details.idUser)
      })
      this.createForm = new FormGroup({
        idComment: new FormControl(''),
        content: new FormControl(''),
        user: new FormGroup({
          idUser: new FormControl(this.idCheck)
        }),
        post: new FormGroup({
          idPost: new FormControl(this.details.idPost)
        })
      })

    })


  }

  showMany(event: any) {
    this.arrayImg = event.target.files
    if (event.target.files.length > 0) {
      for (let i = 0; i < event.target.files.length; i++) {
        if (event.target.files && event.target.files[i]) {
          var reader = new FileReader();
          reader.onload = (event: any) => {
            this.loadArr.push(event.target.result);
            console.log(this.loadArr)
          }
          reader.readAsDataURL(event.target.files[i]);
        }
      }
    }

  }

  async takeImg() {
    for (let i = 0; i < this.arrayImg.length; i++) {
      await this.createImg(this.arrayImg[i])
    }

  }

  createImg(imgIn: any) {
    return new Promise((resolve, rejects) => {
      const n = Date.now();
      const nameImg = `img/${n}` + imgIn.name;
      const fileRef = this.storage.ref(nameImg);
      const task = this.storage.upload(nameImg, imgIn);
      task.snapshotChanges().pipe(finalize(() => {
          fileRef.getDownloadURL().subscribe(url => {
            this.formUpdate.patchValue(({media: url}))
            resolve(true)
            this.linkUp += (url + ",");
          })
        })
      ).subscribe(() => {
      })
    })
  }

  modalTakeInfo(details: NewFeed) {
    this.modalInfo = details;
    console.log(this.modalInfo)
  }

  onSubmit() {
    this.ngxUiLoaderService.start()
    this.takeImg().then(() => {
      if (this.linkUp == "") {
        this.formUpdate.value.media = this.details.media
      } else {
        this.formUpdate.value.media = this.linkUp
      }
      console.log(this.formUpdate.value)
      this.service.updatePost(this.formUpdate.value).subscribe(() => {
        this.ngOnInit()
      });
    }).finally(() => {
      this.ngxUiLoaderService.stop()
    })


  }

  createComment() {
    this.service.addComment(this.createForm.value).subscribe(n => {
    }, err => {
    }, () => {
      this.getCommentCreate()
      this.createForm.reset()
      this.callForm()
    })
  }

  callForm() {
    this.createForm = new FormGroup({
      idComment: new FormControl(),
      content: new FormControl(),
      user: new FormGroup({
        idUser: new FormControl(this.idCheck)
      }),
      post: new FormGroup({
        idPost: new FormControl(this.details.idPost)
      })
    })
  }

  getCommentCreate() {
    this.service.getComment(this.id).subscribe(data => {
      this.listComment = data;
      console.log(this.listComment)
    }, () => {
    }, () => {
      if (this.listComment) {
        this.length = this.listComment.length;

      }
    });
  }


  resetIng() {
    this.loadArr  = [];
  }
}
