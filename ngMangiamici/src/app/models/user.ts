export class User {

  id: number;
  password: string;
  username: string;

  constructor (id?: number, username?: string, password?: string) {
    this.id = id;
    this.password = password;
    this.username = username;

  }

}
