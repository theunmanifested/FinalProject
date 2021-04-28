import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Review } from 'src/app/models/review';
import { ReviewService } from 'src/app/services/review.service';
import { Restaurant } from 'src/app/models/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {


  restaurant: Restaurant = null;
  currentUser: User = null;
  imgDiv = false;



  // 6: friend reviews show up first
  // 7: non friend reviews show up next
  // 8: do some quick refining on th queries for the corner cases

  reviewsByFriends: Review[] = [];
  // ^ first stage: This is all the reviews
  // ^ second stage - just friend reviews;

  reviewsByNonFriendsPublic: Review[] = [];
  newReview: Review = null;
  editReview: Review = null;
  selectedReview = null;

  constructor(
    private reviewService: ReviewService,
    private restaurantService: RestaurantService,
    private currentRoute: ActivatedRoute,
    private userService: UserService,
    private authService: AuthService
  ) { }

  // to display all the reviews or logically display  public / private reviews
  // upon starting this component.
  ngOnInit(): void {

    let restaurantId = this.currentRoute.snapshot.paramMap.get("id");
    if(restaurantId){

      this.loadPage(restaurantId);
    }
    this.loadUser();

  }

  loadUser(){

    if(this.authService.checkLogin()){
      this.userService.getLoggedInUser().subscribe(
        data => {
          this.currentUser = data;
          this.loadReviews();
        },
        fail => {
            console.log(fail);
        }
      );
    } else{
      this.loadReviews();
    }
  }

  loadPage(id: string): void {   // load the restaurant, then load the appropriate reviews

    this.restaurantService.show(id).subscribe(
      data => {
        this.restaurant = data;
        this.loadUser();
        console.log(this.restaurant);
      },
      fail => {
        console.log(fail);
      }
    );
  }


  loadReviews(){

    console.log("LOAD REVIEWS");

    if(this.currentUser){

      if(this.currentUser.role === "admin") {
        this.getAllEnabledReviews();
      } else{
        this.getFriendReviews();
        this.getNonFriendPublicReviews();
      }
    } else {

      // no user in session
      this.getPublicReviewsFromRestaurant();
    }
  }

  public getPublicReviewsFromRestaurant(){
    this.reviewService.indexRestaurantXReviews(this.restaurant.id).subscribe(
      data => {
        this.reviewsByNonFriendsPublic = data;
        console.log("public reviews : ");
        console.log(data);

      },
      fail => {
        console.log(fail);
      }
    );
  }

  getAllEnabledReviews(){
    this.reviewService.adminIndex(this.restaurant.id).subscribe(
      data => {
        this.reviewsByFriends = data;
        console.log("friend reviews : ");
        console.log(data);

      },
      fail => {
        console.log(fail);
      }
    );

  }

  getFriendReviews(): void {

    this.reviewService.indexFriends(this.restaurant.id).subscribe(
      data => {
        this.reviewsByFriends = data;
        console.log("friend reviews : ");
        console.log(data);

      },
      fail => {
        console.log(fail);
      }
    );
  }

  getNonFriendPublicReviews() {

    this.reviewService.indexNonFriendsPublic(this.restaurant.id).subscribe(
      data => {
        this.reviewsByNonFriendsPublic = data;
        console.log("friend reviews " + data);

      },
      fail => {
        console.log(fail);
      }
    );
  }
// // review methods
// displayReview(review) {
//   this.selectedReview  = review;
// }

// reloadAllReviews(){
//   this.reviewService.index().subscribe(
//     data => {this.reviews = data},

//     err => {console.error('Error: ' + err)}
//   );
// }

newReviewForm(){
  this.newReview = new Review();
  console.log(this.newReview)
}

submitNewReview(): void {

  this.reviewService.create(this.newReview, this.restaurant, this.currentUser).subscribe(
    data => {
      console.log("NEW REVIEW CREATED");

      this.loadReviews();
      this.newReview = null;
    },
    err => {
      console.log('Error creating review: ' + err)
    }
  );
  this.newReview = new Review();
}

editReviewForm(rev : Review){
  this.editReview = rev;
}

updateReview(editedReview: Review): void {
  this.reviewService.update(editedReview).subscribe(
    data => {
      this.editReview = null;
      this.loadReviews();
    },
    err => {
      console.log('Error updating todo: ' + err);
    }
  );
}

removeReview(id: number): void {
  this.reviewService.destroy(id).subscribe(
    data => {
      this.loadReviews();
    },
    err =>  {
    console.error('Error: ' + err);
    }
  );
}

uploadImages(){
  this.imgDiv = !this.imgDiv;
}

userTagged(username: string, tagged: string){
  console.log(tagged.includes(username));
  return tagged.includes(username);
}

}
