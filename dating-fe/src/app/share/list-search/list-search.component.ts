import {Component, OnInit} from '@angular/core';
import {User} from "../../user/model/user";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {UserService} from "../service/user.service";


@Component({
  selector: 'app-list-search',
  templateUrl: './list-search.component.html',
  styleUrls: ['./list-search.component.css']
})
export class ListSearchComponent implements OnInit {
  user: User[] = [];
  page: number = 0;
  totalPages: number;
  countTotalPages: number[];
  searchValue: string;

  constructor(private userService: UserService, private toastrService: ToastrService, private  activatedRoute: ActivatedRoute) {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.searchValue = paramMap.get("search");
    })

  }

  ngOnInit() {
    this.getAllPageSearch();

  }

  getAllPageSearch() {
    if (this.searchValue.length > 30) {
      this.toastrService.warning("Bạn đã nhập quá nhiều ký tự")
    }
    if (this.searchValue.match("^\\W+$")) {
      this.toastrService.warning("Không được nhập ký tự đặc biệt")
    }
    else{
      this.userService.getAllSearchPage(this.page,this.searchValue).subscribe(data => {
        if(data == null){
          this.toastrService.warning("Không tìm thấy người dùng tương ứng")
        }else {
          this.user = data.content;
          this.countTotalPages = new Array(data.totalPages)
          this.totalPages = data.totalPages;
        }
        })
    }

  }

  nextPage() {
    if (this.page < this.totalPages) {
      this.page++;
    }
    this.getAllPageSearch();
  }

  previousPage() {
    if (this.page > 0) {
      this.page--;
    }
    this.getAllPageSearch();
  }
}
