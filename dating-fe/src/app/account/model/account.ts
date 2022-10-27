import {User} from '../../user/model/user';
import {AccountRole} from './account-role';

export interface Account {

  idAccount?: number;

  email?: string;

  phone?: string;

  password?: string;

  status?: number;

  idUser?: User;

  accountRoles?: AccountRole[]
}
