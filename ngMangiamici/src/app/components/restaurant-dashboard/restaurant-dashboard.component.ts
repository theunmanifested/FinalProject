import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from 'src/app/models/location';
import { Restaurant } from 'src/app/models/restaurant';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-restaurant-dashboard',
  templateUrl: './restaurant-dashboard.component.html',
  styleUrls: ['./restaurant-dashboard.component.css']
})
export class RestaurantDashboardComponent implements OnInit {

  newRest: Restaurant = new Restaurant();
  newLoc: Location = new Location();
  newUser: User = new User();

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

}
