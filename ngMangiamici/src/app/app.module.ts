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
import { RestaurantComponent } from './components/restaurant/restaurant.component';
import { RegisterComponent } from './components/register/register.component';
import { RestaurantDashboardComponent } from './components/restaurant-dashboard/restaurant-dashboard.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderSearchComponent } from './components/header-search/header-search.component';
import { FormsModule } from '@angular/forms';

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
    HeaderSearchComponent
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
