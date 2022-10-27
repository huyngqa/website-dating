import {User} from '../../user/model/user';

export interface FriendList {

  id?: number;

  status?: number;

  idUser1: User;

  idUser2: User;
}
