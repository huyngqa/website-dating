import {Post} from './post';
import {User} from './user';

export interface Comment {

  idComment?: number;

  content?: string;

  post?: Post;

  user?: User;

}
