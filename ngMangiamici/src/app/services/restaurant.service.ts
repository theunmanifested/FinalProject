import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private url = environment.baseUrl + 'api/restaurant';

  constructor() { }
}
