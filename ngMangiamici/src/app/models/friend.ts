import { FriendId } from "./friend-id";
import { User } from "./user";

export class Friend {
  id: FriendId;
  otherUser: User;
  user: User;
  approved: boolean;

  constructor(id?: FriendId, otherUser?: User, user?: User, approved?: boolean) {
    this.id = id;
    this.otherUser = otherUser;
    this.user = user;
    this.approved = approved;
  }
}
