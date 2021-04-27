import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PublicLandingComponent } from './components/public-landing/public-landing.component';
import { RestaurantDashboardComponent } from './components/restaurant-dashboard/restaurant-dashboard.component';
import { RestaurantComponent } from './components/restaurant/restaurant.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { ContactComponent } from './components/contact/contact.component';
import { FriendsComponent } from './components/friends/friends.component';

const routes: Routes = [

  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: PublicLandingComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'restdash', component: RestaurantDashboardComponent },
  { path: 'restaurants', component: RestaurantComponent},
  { path: 'restaurants/:id', component: RestaurantComponent},
  { path: 'users', component: UserProfileComponent},
  { path: 'friends', component: FriendsComponent},
  { path: '**', component: NotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true})],
  exports: [RouterModule]
})

export class AppRoutingModule { }
