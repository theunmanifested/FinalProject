import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-public-landing',
  templateUrl: './public-landing.component.html',
  styleUrls: ['./public-landing.component.css']
})
export class PublicLandingComponent implements OnInit {

  constructor(private auth: AuthService) { }

  loggedIn() {
    return this.auth.checkLogin();
  }

  ngOnInit(): void {
  }

}
