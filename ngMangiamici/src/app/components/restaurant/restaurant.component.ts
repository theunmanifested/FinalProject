import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Review } from 'src/app/models/review';
import { ReviewService } from 'src/app/services/review.service';
import { Restaurant } from 'src/app/models/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {


  restaurant: Restaurant = null;

  //first step -> show restarant
  //second step show some reviews
  // 3: Create
  // 4: update
  // 5: delete
  // 6: friend reviews show up first
  reviewsByFriends: Review[] = [];
  reviewsByNonFriendsPublic: Review[] = [];
  newReview: Review = new Review();
  editReview: Review = null;
  selectedReview = null;

  constructor(
    private reviewService: ReviewService,
    private restaurantService: RestaurantService,
    private currentRoute: ActivatedRoute
  ) { }

  // to display all the reviews or logically display  public / private reviews
  // upon starting this component.
  ngOnInit(): void {

    let restaurantId = this.currentRoute.snapshot.paramMap.get("id");
    if(restaurantId){

      this.getRestaurant(restaurantId);
    }

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

// addReview(): void {
//   console.log(this.newReview);
//   this.reviewService.create(this.newReview).subscribe(
//     data => {
//       this.reloadAllReviews();
//     },
//     err => {
//       console.log('Error creating review: ' + err)
//     }
//   );
//   this.newReview = new Review();
// }

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
