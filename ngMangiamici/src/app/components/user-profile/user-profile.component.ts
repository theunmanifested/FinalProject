import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  currentUser: User = new User();

  isEditing: boolean=false;

  toggleIsEditing(): void {
    if (this.isEditing===true) {
      this.isEditing=false;
    }
    else {
      this.isEditing=true;
    }
  }

  constructor(
    private restaurantSvc: RestaurantService,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) { }

  update(): void {
    this.userService.updateUser(this.currentUser).subscribe(
      data => {
        this.currentUser = data;
        this.loadUser();
      },
      fail => {
      }
    );
  }

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser(){
    this.userService.getLoggedInUser().subscribe(
      data => {
        this.currentUser = data;
      },
      fail => {
      }
    );
  }



}
