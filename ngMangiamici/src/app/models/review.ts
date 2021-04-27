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
  createdDate: string;
  updateDate: string;


  constructor(id?: number, user?: User, restaurant?: Restaurant, reviewText?: string,
              enabled?: boolean, isPublic?: boolean, rating?: number, createDate?: string, updateDate?: string) {

    this.id = id;
    this.user = user;
    this.restaurant = restaurant;
    this.reviewText = reviewText;
    this.enabled = enabled;
    this.isPublic = isPublic;
    this.rating = rating;

    this.createdDate = createDate;
    this.updateDate = updateDate;
    this.enabled = enabled;
  }
}
