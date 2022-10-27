import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {UserServiceService} from "../../service/user-service.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {
  keyword: string = "";
  page: number = 0;
  totalPage: number;
  userList: User[] = [];
  reportDetailList: any = null;
  selectedMember = "";
  selectWarning: "";
  user: User = null;

  constructor(private userService: UserServiceService,private toast:ToastrService, private router: Router) {
  }

  ngOnInit(): void {
    this.searchAndListUser();
    this.updateStatus();
  }

  searchAndListUser() {
    this.page = 0;
    return this.userService.findByAllAndSearchNameUser(this.keyword, this.selectedMember, this.page).subscribe(us => {
     if(us){
       // @ts-ignore
       this.userList = us.content;
       // @ts-ignore
       this.totalPage = us.totalPages;
     }
     else {
       this.toast.success("Tên bạn tìm không có trong danh sách","Thông báo")
       this.userList = [];
       this.totalPage = 0;
     }

    }, error => {
      this.router.navigateByUrl('/share/error');
      this.toast.error('Bạn không có quyền vào trang này', "Thông báo")
    })
  }

  previous() {
    this.page = this.page - 1;
    return this.userService.findByAllAndSearchNameUser(this.keyword, this.selectedMember, this.page).subscribe(us => {
      // @ts-ignore
      this.userList = us.content;
      // @ts-ignore
      this.totalPage = us.totalPages;
    })
  }

  next() {
    this.page = this.page + 1;
    return this.userService.findByAllAndSearchNameUser(this.keyword, this.selectedMember, this.page).subscribe(us => {
      // @ts-ignore
      this.userList = us.content;
      // @ts-ignore
      this.totalPage = us.totalPages;
    })
  }

  getReportDetail(id: number) {
    this.userService.getAllReportDetail(id).subscribe(rd => {
      this.reportDetailList = rd
      this.userService.findByIdUser(id).subscribe(us => {
        this.user = us;
        console.log(us)
      })
    }, error => {
      this.router.navigateByUrl('/share/error');
      this.toast.error('Bạn không có quyền vào trang này', "Thông báo")
    });
  }

  updateStatus() {
    if(this.reportDetailList){
      const request = {
        idUser: this.reportDetailList[0].idUser,
        status: this.selectWarning
      }
      this.userService.updateStatusWarrningUser(request).subscribe(() => {
        this.toast.success("Gửi cảnh cáo thành công!","Thông báo");
      }, error => {
        this.router.navigateByUrl('/share/error');
        this.toast.error('Bạn không có quyền vào trang này', "Thông báo")
      });
    }
  }


}
