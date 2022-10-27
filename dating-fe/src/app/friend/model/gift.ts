import {GiftUser} from './gift-user';

export interface Gift {

  idGift?: number;

  giftName?: string;

  price?: number;

  giftUsers?: GiftUser[]
}
