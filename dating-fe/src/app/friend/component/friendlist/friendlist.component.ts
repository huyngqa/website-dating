import {Component, OnInit} from '@angular/core';
import {FriendListService} from "../../friend-service/friend-list.service";
import {User} from "../../../user/model/user";
import {ToastrService} from "ngx-toastr";
import {FormControl, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../../service/authentication/token-storage.service";
import {AuthenticationService} from "../../../service/authentication/authentication.service";
import Swal from 'sweetalert2';
import {Gift} from "../../model/gift";
import {GiftService} from "../../friend-service/gift.service";
import {GiftUserService} from "../../friend-service/gift-user.service";

@Component({
  selector: 'app-friendlist',
  templateUrl: './friendlist.component.html',
  styleUrls: ['./friendlist.component.css']
})
export class FriendlistComponent implements OnInit {
  listFriend: any;
  page: number = 0;
  idArr: number [] = [];
  check: any[] = [];
  size = 4;
  myIdUser;
  informationDelete: User[] = [];
  friendDeleted: User;
  name = '';
  account: any;
  searchForm = new FormGroup({
    name: new FormControl('')
  });

  gifList: Gift[] = [];
  idGift: number;
  quantity = 1;

  idUserReceiver: number;
  coin: number;
  price: number;
  gift: Gift;


  constructor(private friendListService: FriendListService,
              private toast: ToastrService,
              private tokens: TokenStorageService,
              private giftService: GiftService,
              private giftUserService: GiftUserService,
              private auth: AuthenticationService) {
    this.myIdUser = this.tokens.getUser().idAccount;

  }

  ngOnInit(): void {
    this.auth.getUserByAccount(this.tokens.getUser().idAccount).subscribe(data => {
      this.myIdUser = data.idUser;
      console.log(this.myIdUser)
      this.getAll(this.size);
      this.getAllGift();
    })


  }

  getAll(size: number) {

    return this.friendListService.getFriendList(this.myIdUser, this.page, this.name, size).subscribe(n => {
      if (n === null) {
        this.listFriend = [];
        // this.toast.warning("Kh??ng c?? b???n b??", "Ch?? ??")
      } else {
        this.listFriend = n.content;
      }
      console.log(n)
    })
  }

  deleteFriendList() {

    Swal.fire({
      title: 'B???n c?? mu???n xo?? kh??ng?',
      text: "Ti???n tr??nh kh??ng th??? ho??n t??c!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '?????ng ??!',
      cancelButtonText: 'T??? ch???i'
    }).then((result) => {
      if (result.isConfirmed) {
        const id: number[] = [];
        for (const argument of this.informationDelete) {
          id.push(argument.idUser);
          console.log(id)
        }
        if (id.length > 0) {
          this.friendListService.deleteFriendList(this.myIdUser, id).subscribe(value => {
            this.name = '';
            this.getAll(this.size);
            Swal.fire(
              '???? xo??!',
              '???? xo?? th??nh c??ng.',
              'success'
            )
          }, error => {
            this.toast.error("X??a b???n b?? kh???i danh s??ch kh??ng th??nh c??ng", "X??a th???t b???i")
          })
        }

      }
    })

  }

  blockFriendList() {
    Swal.fire({
      title: 'B???n c?? ch???n t??i kho???n ???? ch???n kh??ng?',
      text: "Ti???n tr??nh kh??ng th??? ho??n t??c!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '?????ng ??!',
      cancelButtonText: 'T??? ch???i'
    }).then((result) => {
      if (result.isConfirmed) {
        const id: number[] = [];
        for (const argument of this.informationDelete) {
          id.push(argument.idUser);
          console.log(id)
        }
        if (id.length > 0) {
          this.friendListService.blockFriendList(this.myIdUser, id).subscribe(value => {
            this.name = '';
            this.getAll(this.size);
            Swal.fire(
              '???? ch???n!',
              '???? ch???n xem b??i ????ng th??nh c??ng.',
              'success'
            )
          }, error => {
            // alert("chan thanh cong")
            this.toast.error("Ch???n b???n b?? th???t b???i r???i", "Ch???n th???t b???i")
          })
        }

      }
    })

  }

  checkbox(listFriend: number) {
    for (const item of this.informationDelete) {
      if (item.idUser === listFriend) {
        return true;
      }
    }
    return false;
  }

  checkList(listFriend: User) {
    this.friendDeleted = this.informationDelete.find(deleteObject => deleteObject.idUser === listFriend.idUser);
    if (this.friendDeleted) {
      this.informationDelete = this.informationDelete.filter(friendDelete => friendDelete.idUser !== this.friendDeleted.idUser);
    } else {
      this.informationDelete.push(listFriend)
    }
  }

  more() {
    this.size += 4;
    this.getAll(this.size);
  }


  search() {
    this.size = 4;
    this.name = this.searchForm.value.name.trim();
    this.getAll(this.size)
  }

  getAllGift() {
    this.giftService.getAllGift().subscribe(gift => {
      this.gifList = gift;
    });
  }

  getUser(l: any) {
    this.idUserReceiver = l.idUser;
    console.log(this.idUserReceiver)
  }

  getGift(i: any) {
    this.idGift = i.idGift
    this.auth.getUserByAccount(this.tokens.getUser().idAccount).subscribe(data => {
      this.myIdUser = data.idUser;
      console.log(this.myIdUser);
      this.giftUserService.findByIdGift(this.idGift).subscribe((gift: any) => {
        this.price = gift.price;
        this.giftUserService.findByIdUser(this.myIdUser).subscribe((user: any) => {
          console.log(user);
          this.coin = user.coin;
          console.log(this.price);
          console.log(this.coin);
          if (this.coin < this.price) {
            this.toast.error("S??? ti???n kh??ng ????? ! B???n c???n n???p th??m !", "Th??ng b??o !")
          } else {
            this.giftUserService.giveAGiftUser(this.idGift, this.myIdUser, this.idUserReceiver,  this.quantity).subscribe(() => {
              this.toast.success("T???ng qu?? th??nh c??ng", "Th??ng b??o !")
            })
          }
        });
      });
    });
  }
}
