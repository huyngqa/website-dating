import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChatRoutingModule } from './chat-routing.module';
import { ReadMessageComponent } from './component/read-message/read-message.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ShareModule} from "../share/share.module";


@NgModule({
  declarations: [ReadMessageComponent],
    imports: [
        CommonModule,
        ChatRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        ShareModule
    ]
})
export class ChatModule { }
