import { User } from "./user";

export class Location {

  id: number;
  user: User;
  address: string;
  city: string;
  state: string;
  zip: string;
  latitude: number;
  longitude: number;
  phone: string;
  isPublic: boolean;

  constructor(id?: number, user?: User, address?: string, city?: string, state?: string, zip?: string,
              latitude?: number, longitude?: number, phone?: string, isPublic?: boolean) {

  this.id = id;
  this.user = user;
  this.address = address;
  this.city = city;
  this.state = state;
  this.zip = zip;
  this.latitude = latitude;
  this.longitude = longitude;
  this.phone = phone;
  this.isPublic = isPublic;
  }

}
