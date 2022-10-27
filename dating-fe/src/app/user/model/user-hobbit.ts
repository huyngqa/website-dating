import {UserHobbitKey} from './user-hobbit-key';
import {Hobbit} from './hobbit';
import {User} from './user';

export interface UserHobbit {

  id?: UserHobbitKey;

  hobbit?: Hobbit;

  user?: User;

}
