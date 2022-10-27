import {User} from './user';

export interface Invoice {

  id?: number;

  price?: number;

  time?: string;

  user?: User;
}
