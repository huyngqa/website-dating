import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShareRoutingModule } from './share-routing.module';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ListSearchComponent } from './list-search/list-search.component';
import {HttpClientModule} from "@angular/common/http";
import { Error404Component } from './error404/error404.component';
import {FormsModule} from "@angular/forms";
import { MenuComponent } from './menu/menu.component';
import { ChatBoxComponent } from './chat-box/chat-box.component';
import { ChatBoxOnlyComponent } from './chat-box-only/chat-box-only.component';
import { ErrorReportComponent } from './error-report/error-report.component';



@NgModule({
  declarations: [HomeComponent, HeaderComponent, FooterComponent, ListSearchComponent, Error404Component, MenuComponent, ChatBoxComponent, ChatBoxOnlyComponent, ErrorReportComponent],
  exports: [
    HeaderComponent,
    FooterComponent,
    ChatBoxComponent,
    MenuComponent,
    ChatBoxOnlyComponent
  ],
  imports: [
    CommonModule,
    ShareRoutingModule,
    HttpClientModule,
    FormsModule,

  ]
})
export class ShareModule { }
