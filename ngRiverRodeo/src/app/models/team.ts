export class Team {
  id: number;
  name: string;
  password: string;
  phoneNumber: string;
  rank: number;
  role: string;
  pictureUrl: string;
  enabled: boolean;

  constructor(  id?: number,name?: string,password?: string,phoneNumber?: string,rank?: number,role?: string,pictureUrl?: string,enabled?: boolean){
    this.id = id;
    this.name = name;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.rank = rank;
    this.role = role;
    this.pictureUrl = pictureUrl;
    this.enabled = enabled;
  }

}
