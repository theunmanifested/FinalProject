import { Component, OnInit } from '@angular/core';
import { Restaurant } from 'src/app/models/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-header-search',
  templateUrl: './header-search.component.html',
  styleUrls: ['./header-search.component.css']
})
export class HeaderSearchComponent implements OnInit {

  searchActive: boolean = false;
  searchTerm: string;
  searchResults: Restaurant[] = [];

  constructor(
    private restaurantSvc: RestaurantService
  ) { }


  getSearchActive() {
    return this.searchActive;
  }

  searchActiveToggle() {
    if(this.searchActive === true) {
      this.searchActive = false;
    }
    if(this.searchActive ===false) {
      this.searchActive = true;
    }
  }


  search() {
    //this.searchResults = this.restaurantSvc.
  }

  ngOnInit(): void {
  }

}
