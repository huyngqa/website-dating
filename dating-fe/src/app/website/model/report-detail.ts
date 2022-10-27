import {Post} from '../../user/model/post';
import {User} from '../../user/model/user';
import {Report} from './report';

export interface ReportDetail {

  id?: number;

  post?: Post;

  reporter?: User;

  report?: Report;

  status?: number;

  timeReport?: string;

}
