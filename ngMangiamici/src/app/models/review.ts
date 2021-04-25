import { Restaurant } from "./restaurant";
import { User } from "./user";

export class Review {
  id: number;
  user: User;
  restaurant: Restaurant;
  reviewText: string;
  enabled: boolean;
  isPublic: boolean;
  rating: number;

  constructor(id?: number, user?: User, restaurant?: Restaurant, reviewText?: string,
              enabled?: boolean, isPublic?: boolean, rating?: number) {

    this.id = id;
    this.user = user;
    this.restaurant = restaurant;
    this.reviewText = reviewText;
    this.enabled = enabled;
    this.isPublic = isPublic;
    this.rating = rating;
  }
}
