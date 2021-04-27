import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AboutComponent } from './components/about/about.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { PublicLandingComponent } from './components/public-landing/public-landing.component';
import { PrivateLandingComponent } from './components/private-landing/private-landing.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

import { RegisterComponent } from './components/register/register.component';
import { RestaurantDashboardComponent } from './components/restaurant-dashboard/restaurant-dashboard.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormsModule } from '@angular/forms';
import { HeaderSearchComponent } from './components/header-search/header-search.component';
import { RestaurantComponent } from './components/restaurant/restaurant.component';
import { FriendsComponent } from './components/friends/friends.component';
import { ContactComponent } from './components/contact/contact.component';

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    AboutComponent,
    NavBarComponent,
    HeaderComponent,
    FooterComponent,
    PublicLandingComponent,
    PrivateLandingComponent,
    UserProfileComponent,
    RestaurantComponent,
    RegisterComponent,
    RestaurantDashboardComponent,
    HeaderSearchComponent,
    FriendsComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
