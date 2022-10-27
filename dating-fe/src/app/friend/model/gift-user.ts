import {Gift} from './gift';
import {User} from '../../user/model/user';
import {GiftUserKey} from './gift-user-key';

export interface GiftUser {

  id: GiftUserKey;

  gift?: Gift;

  sender?: User;

  receiver?: User;

  quantity?: number;
}
