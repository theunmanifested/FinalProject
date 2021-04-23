import { Component, OnInit } from '@angular/core';
import { Restaurant } from 'src/app/models/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-header-search',
  templateUrl: './header-search.component.html',
  styleUrls: ['./header-search.component.css']
})
export class HeaderSearchComponent implements OnInit {

  constructor(
    private restaurantSvc: RestaurantService
  ) { }

  searchTerm: string;

  searchResults: Restaurant[] = [];

  search() {
    //this.searchResults = this.restaurantSvc.
  }

  ngOnInit(): void {
  }

}
