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

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {


  restaurant: Restaurant = null;
  currentUser: User = null;

  //first step -> show restarant
  //second step show some reviews
  // 3: Create
  // 4: update
  // 5: delete
  // 6: friend reviews show up first

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
    private userService: UserService
  ) { }

  // to display all the reviews or logically display  public / private reviews
  // upon starting this component.
  ngOnInit(): void {

    let restaurantId = this.currentRoute.snapshot.paramMap.get("id");
    if(restaurantId){

      this.getRestaurant(restaurantId);
    }

    this.loadReviews();
    this.loadUser();

  }

  loadUser(){
    this.userService.getLoggedInUser().subscribe(
      data => {
        this.currentUser = data;
      },
      fail => {
          console.log(fail);
      }
    );
  }

  getRestaurant(id: string): void {

    this.restaurantService.show(id).subscribe(
      data => {
        this.restaurant = data;
        console.log(this.restaurant);

      },
      fail => {
        console.log(fail);
      }
    );
  }


  loadReviews(){
    this.getFriendReviews();
    this.getNonFriendPublicReviews();
  }

  // for now this just gets all reviews.
  getFriendReviews(): void {

    // for now this just gets all reviews.

    this.reviewService.index().subscribe(
      data => {
        this.reviewsByFriends = data;
      },
      fail => {
        console.log(fail);
      }
    );
  }

  getNonFriendPublicReviews() {

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
      this.loadReviews();
      this.newReview = null;
    },
    err => {
      console.log('Error creating review: ' + err)
    }
  );
  this.newReview = new Review();
}

// updateReview(editedReview: Review, displayReview = true): void {
//   this.reviewService.update(editedReview).subscribe(
//     data => {
//       if(displayReview) {
//         this.selectedReview = editedReview;
//       }
//       this.editReview = null;
//       this.reloadAllReviews();
//     },
//     err => {
//       console.log('Error updating todo: ' + err);
//     }
//   );
// }

// deletedTodo(id: number): void {
//   this.reviewService.destroy(id).subscribe(
//     data => {
//       this.reloadAllReviews();
//     },
//     err =>  {
//     console.error('Error: ' + err);
//     }
//   );
// }

}
