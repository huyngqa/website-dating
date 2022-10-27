import {User} from './user';

export interface Post {

  idPost?: number;

  content?: string;

  media?: string;

  time?: string;

  user?: User;

  comments?: Comment[];
}
