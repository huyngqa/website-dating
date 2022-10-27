import {Role} from './role';
import {AccountRoleKey} from './account-role-key';

export interface AccountRole {

  id?: AccountRoleKey;

  account?: Account;

  role?: Role;
}
