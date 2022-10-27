import {User} from '../../user/model/user';
import {RoomChat} from './room-chat';

export interface Message {

  idRoomChat?: RoomChat;

  idUser?: User;

  message?: string;

  status?: number;
}
