import { Favoriterestaurant } from "./favoriterestaurant";
import { Location } from "./location";
import { Review } from "./review";
import { User } from "./user";

export class Restaurant {
  id: number;
  user: User;
  location: Location;
  reviews: Review[];
  phone: string;
  email: string;
  websiteUrl: string;
  categories: string;
  imgUrl: string;
  name: string;
  description: string;
  militaryDiscount: boolean;
  firstresponderDiscount: boolean;
  seniorDiscount: boolean;
  menuUrl: string;
  favoriteRestaurants: Favoriterestaurant[];


  constructor(id?: number, user?: User, location?: Location,
    reviews?: Review[], phone?: string, email?: string, websiteUrl?: string, categories?: string,
    imgUrl?: string, name?: string, description?: string, militaryDiscount?: boolean,
    firstresponderDiscount?: boolean, seniorDiscount?: boolean, menuUrl?: string,
    favoriteRestaurants?: Favoriterestaurant[]) {

    this.id = id;
    this.user = user;
    this.location = location;
    this.reviews = reviews;
    this.phone = phone;
    this.email = email;
    this.websiteUrl = websiteUrl;
    this.categories = categories;
    this.imgUrl = imgUrl;
    this.name = name;
    this.description = description;
    this.militaryDiscount = militaryDiscount;
    this.firstresponderDiscount = firstresponderDiscount;
    this.menuUrl = menuUrl;
    this.favoriteRestaurants = favoriteRestaurants;
  }
}



