import { Restaurant } from "./restaurant";
import { User } from "./user";

export class Post {

  id: number;
  user: User;
  postText: string;
  flagged: boolean;
  enabled: boolean;
  restaurant: Restaurant;

  constructor(id?: number, user?: User, postText?: string, flagged?: boolean,
              enabled?: boolean, restaurant?: Restaurant) {

  this.id = id;
  this.user = user;
  this.postText = postText;
  this.flagged = flagged;
  this.enabled = enabled;
  this.restaurant = restaurant;
  }

}
