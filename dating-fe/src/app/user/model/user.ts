import {StatusActive} from './status-active';
import {TypeUser} from './type-user';
import {UserHobbit} from './user-hobbit';
import {Post} from './post';
import {Invoice} from './invoice';
import {UserTarget} from './user-target';
import {GiftUser} from '../../friend/model/gift-user';
import {Account} from "../../account/model/account";

export interface User {

  idUser?: number;

  name?: string;

  dateOfBirth?: string;

  gender?: boolean;

  address?: string;

  job?: string;

  married?: boolean;

  avatar?: string;

  joinDay?: string;

  coin?: number;

  statusActive?: StatusActive;

  account?: Account;

  typeUser?: TypeUser;

  userHobbits?: UserHobbit[];

  postList?: Post[];

  comments?: Comment[];

  invoices?: Invoice[];

  userTargets?: UserTarget[];

  giftSenders?: GiftUser[];

  giftReceiver?: GiftUser[];
}
