import { UserStatus } from "./user-status";

export class User {

  id: number;
  password: string;
  username: string;
  enabled: boolean;
  role: string;
  firstName: string;
  lastName: string;
  imgUrl: string;
  promoOpt: boolean;
  aboutMe: string;
  userStatus: UserStatus;


  constructor (id?: number, username?: string, password?: string, role?: string,
              firstName?: string, lastName?: string, imgUrl?: string, promoOpt?: boolean,
              aboutMe?: string, userStatus?: UserStatus) {
    this.id = id;
    this.password = password;
    this.username = username;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.imgUrl = imgUrl;
    this.promoOpt = promoOpt;
    this.aboutMe = aboutMe;
    this.userStatus = userStatus;
  }

}
