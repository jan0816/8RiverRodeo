import { Team } from './team';

export class User {
  id: number;
  firstName: string;
  lastName: string;
  team: Team;

constructor(  id?: number,firstName?: string,lastName?: string,team?: Team){

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.team = team;
}

}
