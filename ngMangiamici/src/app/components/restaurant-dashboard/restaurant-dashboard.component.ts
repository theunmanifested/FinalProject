import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from 'src/app/models/location';
import { Restaurant } from 'src/app/models/restaurant';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-restaurant-dashboard',
  templateUrl: './restaurant-dashboard.component.html',
  styleUrls: ['./restaurant-dashboard.component.css']
})
export class RestaurantDashboardComponent implements OnInit {

  newRest: Restaurant = new Restaurant();
  newLoc: Location = new Location();
  currentUser: User = new User();

  constructor(
    private authService: AuthService,
    private http: HttpClient,
    private router: Router,
    private restService: RestaurantService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.loadUser();
    console.log(this.currentUser);
  }

// add Restaurant
  addRest(): void {
    this.newRest.location = this.newLoc;
    // how to get this user
    this.newRest.user = this.currentUser;
    console.log(this.newRest);
    this.restService.create(this.newRest).subscribe(
      data => {
        this.loadUser();
      },
      err => {
        console.log('Error creating restaurant: ' + err)
      }
    );
  }



// Get user from whomever is logged in .. see user.service.ts
loadUser(){
  this.userService.getLoggedInUser().subscribe(
    data => {
      this.currentUser = data;
      console.log(this.currentUser);
    },
    fail => {
    }
  );
}

private getHttpOptions() {
  // Send credentials as Authorization header (this is spring security convention for basic auth)
  const credentials = this.authService.getCredentials();
  const httpOptions = {
    headers: new HttpHeaders({
      'Authorization': `Basic ${credentials}`,
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return httpOptions;
}

}
