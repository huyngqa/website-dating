import {AfterViewChecked, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserServiceService} from "../../user/service/user-service.service";
import {FriendService} from "../../user/service/friend.service";
import {SuggestService} from "../../friend/friend-service/suggest.service";
import {FriendListService} from "../../friend/friend-service/friend-list.service";
import {ToastrService} from "ngx-toastr";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../../service/authentication/token-storage.service";
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {AngularFirestore} from "@angular/fire/firestore";
import {User} from "../../user/model/user";
import firebase from "firebase/app";
import "firebase/database";
import firestore = firebase.firestore;

@Component({
  selector: 'app-chat-box',
  templateUrl: './chat-box.component.html',
  styleUrls: ['./chat-box.component.css']
})
export class ChatBoxComponent implements OnInit, AfterViewChecked {
  groups: any[] = [];
  user: any;
  input: string = '';
  messages: any[] = [];
  user1;
  user2;
  user2Avatar: string
  user1Avatar
  room: string
  flag = true
  user2Name: string

  @ViewChild('scrollMe') private myScrollContainer: ElementRef;

  constructor(private service: UserServiceService,
              private friendService: FriendService,
              private suggestService: SuggestService,
              private friendListService: FriendListService,
              private toast: ToastrService, private http: HttpClient,
              private tokens: TokenStorageService,
              private auth: AuthenticationService,
              public database: AngularFirestore,) {
    this.http.get<User>('http://localhost:8080/api/users/my-user/' + this.tokens.getUser().idAccount, this.auth.getToken()).subscribe(n => {
      this.user = n
      this.user1 = this.user.idUser
      this.user1Avatar = this.user.avatar
      this.getAll(10)
    })
  }

  ngOnInit(): void {

  }

  async sendMessages() {
    console.log('1')
    if (this.input != "") {
      const ref = this.database.collection(`rooms/${this.room}/messages`);
      const sender = this.user1
      const timestamp = new Date().getTime()
      const content = this.input
      const seen = false
      ref.add({sender, timestamp, content, seen});
    }
    this.input = ""
    const ref = firestore().collection(`rooms/${this.room}/messages`).onSnapshot(n => {
      console.log('test log sendmesssaage', n)
    })
  }

  async delete() {
    const deleteArr = []
    const db = this.database.collection(`rooms/${this.room}/messages`)
    this.database.collection(`rooms/${this.room}/messages`).get().subscribe(n => {
      n.forEach(docs => {
        db.doc(docs.id).delete();
      })
    })


    this.loadMessage(this.user2, this.user2Avatar,this.user2Name)
  }

  // event scroll
  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    try {
      this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    } catch (err) {
    }
  }


  async chatRoomCheck(user: any) {
    this.user2 = user
    var arrGroup = []
    var arrGroup2 = []
    const fire1 = await firestore().collection("rooms")
      .where("joiner", 'array-contains', this.user1)
      .get()
    const fire2 = await firestore().collection("rooms")
      .where("joiner", 'array-contains', user)
      .get()

    fire1.forEach(n => {
      arrGroup.push(n.id)
    })

    fire2.forEach(res => {
      arrGroup2.push(res.id)
    })
    const result = arrGroup.filter(arr => {
      return arrGroup2.includes(arr)
    })
    if (result.length == 0) {
      firestore().collection('rooms').add({joiner: [this.user1, user]})
      return this.chatRoomCheck(user)
    }

    return result.toString()
  }

  async loadMessage(user: any, avatar: string, name: string) {
    this.input = ''
    document.getElementById('viewChat').style.display = 'block'
    this.user2Name = name
    this.user2Avatar = avatar
    console.log(this.user2Avatar)
    this.room = await this.chatRoomCheck(user)
    this.flag = true;
    this.messages = []
    this.updateMessage()
  }

  updateMessage() {
    firestore().collection(`rooms/${this.room}/messages`)
      .orderBy('timestamp', 'asc')
      .onSnapshot(n => {
        if (this.flag) {
          n.forEach(res => {
            this.messages.push(res.data())
          })
          this.flag = false
        } else {
          this.messages.push(n.docs[n.docs.length - 1].data())
          console.log(this.messages)
          this.input = ""
        }
      })
  }

  getAll(size: number) {
    return this.friendListService.getFriendList(this.user.idUser, 0, "", 5).subscribe(n => {
      if (n === null) {
        this.groups = [];
        this.toast.warning("Không có bạn bè", "Chú ý")
      } else {
        this.groups = n.content;
      }
    })
  }

  closeChat() {
    document.getElementById('viewChat').style.display = 'none'
  }
}
