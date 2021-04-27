import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  user: User = new User();
  loggedUser: User = new User();
  doesIt: boolean = false;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    ) { }

  login() {
    this.authService.login(this.user.username, this.user.password).subscribe(
      data => {
        console.log('Logged in');

        this.router.navigateByUrl('/home')
      },
      err => {
        console.log('Error logging in: ' + err);
      }
    )
  }

  logout(){
    console.log("logout");
    this.authService.logout();
    if(!this.authService.checkLogin()){
      this.router.navigateByUrl('/home');
    }
  }

  loggedIn() {
    return this.authService.checkLogin();
  }

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser(){
    this.userService.getLoggedInUser().subscribe(
      data => {
        this.loggedUser = data;
        // console.log(this.loggedUser);
      },
      fail => {
      }
    );
    // if (this.loggedUser.restaurant) {
    //   this.doesIt = true;
    // } else {
    //   this.doesIt = false;
    // }
  }

  hasRestfunc(){
    // this.loadUser();
    if (this.loggedUser.restaurant) {
      return true;
    } else {
      return false;
    }
  }
}
