import {User} from './user';
import {Target} from './target';
import {UserTargetKey} from './user-target-key';

export interface UserTarget {

  id?: UserTargetKey;

  user?: User;

  target?: Target;
}
