export class FriendId {
  userId: number;
  otherUserId: number;

  constructor(userId?: number, otherUserId?: number) {
    this.userId = userId;
    this.otherUserId = otherUserId;
  }
}
