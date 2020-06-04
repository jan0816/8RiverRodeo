import { User } from './user';
import { River } from './river';

export class Fish {
  id: number;
  sizeInCm: number;
  pictureUrl: string;
  river: River;
  dayCaught: string;
  user: User;



  constructor(id?: number,
    sizeInCm?: number,
    pictureUrl?: string,
    river?: River,
    dayCaught?: string,
    user?: User){

      this.id = id;
      this.sizeInCm = sizeInCm;
      this.pictureUrl= pictureUrl;
      this.river = river;
      this.dayCaught = dayCaught;
      this.user= user;
    }
}
