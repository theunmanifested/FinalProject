import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Friend } from 'src/app/models/friend';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { UserService } from 'src/app/services/user.service';
import { FriendService } from 'src/app/services/friend.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {

  currentUser: User = new User();

  friendlist: Friend[]=[];
  //NEW************************
  pendingFriendList: Friend[]=[];

  constructor(
    private restaurantSvc: RestaurantService,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    private friendService: FriendService
  ) { }

  boolNotSameUser(user: User): boolean {
    if (this.currentUser.id !== user.id) {
      return true;
    }
    else {
      return false;
    }
  }

  ngOnInit(): void {
    this.loadUser();
    //getFriends() used to be here...
  }

  getFriends(): void {
    this.friendService.getFriends(this.currentUser).subscribe(
      data => {
        this.friendlist = data;
      },
      fail => {
      }
    );
  }
  //INCOMPLETE**************************************
  getPendingFriends(): void {
    this.friendService.getFriends(this.currentUser).subscribe(
      data => {
        this.friendlist = data;
      },
      fail => {
      }
    );
  }

  loadUser(){
    this.userService.getLoggedInUser().subscribe(
      data => {
        this.currentUser = data;
        //getFriends() moved to here
        this.getFriends();
      },
      fail => {
      }
    );
  }

}
