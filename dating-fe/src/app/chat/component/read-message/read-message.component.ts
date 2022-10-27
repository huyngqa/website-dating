import {AfterViewChecked, Component, ElementRef, OnChanges, OnInit, ViewChild} from '@angular/core';

import firebase from "firebase/app";
import "firebase/database";
import {HttpClient, HttpEvent} from "@angular/common/http";
import {AngularFirestore, fromCollectionRef} from '@angular/fire/firestore';
import firestore = firebase.firestore;

import {FriendListService} from "../../../friend/friend-service/friend-list.service";
import {ToastrService} from "ngx-toastr";
import {TokenStorageService} from "../../../service/authentication/token-storage.service";
import {AuthenticationService} from "../../../service/authentication/authentication.service";
import {User} from "../../../user/model/user";
import {stringify} from "querystring";


@Component({
  selector: 'app-read-message',
  templateUrl: './read-message.component.html',
  styleUrls: ['./read-message.component.css']
})
export class ReadMessageComponent implements OnInit, AfterViewChecked {
  input: string = '';
  messages: any[] = [];
  user1;
  user2;
  user2Avatar: string
  user1Avatar
  room: string
  groups: any[] = [];
  flag = true
  user: any
  listUnreadMessage = []
  user2Name: string
search: string
  @ViewChild('scrollMe') private myScrollContainer: ElementRef;

  constructor(public database: AngularFirestore, private http: HttpClient, private friendListService: FriendListService, private toast: ToastrService,
              private tokens: TokenStorageService, private auth: AuthenticationService) {
    http.get<User>('http://localhost:8080/api/users/my-user/' + this.tokens.getUser().idAccount, this.auth.getToken()).subscribe(n => {
      this.user = n
      this.user1 = this.user.idUser
      this.user1Avatar = this.user.avatar
      this.getAll(10)
    })


  }

  ngOnInit(): void {
    this.scrollToBottom();
    this.listObserver()

  }

  async sendMessages() {
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
    this.loadMessage(this.user2,this.user2Avatar,this.user2Name)
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
    this.user2Name = name
    this.user2Avatar = avatar
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

  async listObserver() {
    // firestore().collection('rooms')
    //   .where('join','array-contains',this.user1)
    //   .onSnapshot(n =>{
    //     this.listUnreadMessage.push(n.docs[n.docs.length-1].data())
    //   })
    const roomts = await this.database.collection('rooms').get()

    roomts.forEach(doc => {
      console.log(doc)
    })
  }

  getAll(size: number) {
    return this.friendListService.getFriendList(this.user.idUser, 0, "", 10).subscribe(n => {
      if (n === null) {
        this.groups = [];
        this.toast.warning("Không có bạn bè", "Chú ý")
      } else {
        this.groups = n.content;
      }
    })
  }

  searchUser(){
    this.http.get("http://localhost:8080/api/public/searchPage?name="+this.search+"&size=1000",this.auth.getToken())
      .subscribe(n=>{
       // @ts-ignore
        this.groups= n.content
      })
  }
}
