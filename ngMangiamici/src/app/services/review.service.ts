import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Review } from '../models/review';
import { Restaurant } from '../models/restaurant';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private url = environment.baseUrl + 'api/reviews/';


  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }


  // get all public reviews.
  index(): Observable<Review[]> {
    return this.http.get<Review[]>(environment.baseUrl + "api/pub/reviews/all")
    .pipe(
      catchError((err:any) => {
         console.log(err);
         return throwError('Error getting Reviews');
      })
     );
  }

   // get all public reviews for a given restaurant
  indexRestaurantXReviews(rId): Observable<Review[]> {
    return this.http.get<Review[]>(environment.baseUrl + "api/pub/reviews/" + rId)
    .pipe(
      catchError((err:any) => {
         console.log(err);
         return throwError('Error getting Reviews');
      })
     );
  }


   // get all reviews.
  adminIndex(rId: number): Observable<Review[]> {

    return this.http.get<Review[]>(environment.baseUrl + "api/review/" + rId + "/admin", this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
         console.log(err);
         return throwError('Error getting Reviews');
      })
     );
  }

    // get all friend reviews.
    indexFriends(rId: number): Observable<Review[]> {
      return this.http.get<Review[]>(this.url + "friends/" + rId, this.getHttpOptions())
      .pipe(
        catchError((err:any) => {
           console.log(err);
           return throwError('Error getting Reviews');
        })
       );
    }

    indexNonFriendsPublic(restaurantId: number): Observable<Review[]>{
      return this.http.get<Review[]>(this.url + "otherPublic/" + restaurantId, this.getHttpOptions())
      .pipe(
        catchError((err:any) => {
           console.log(err);
           return throwError('Error getting Reviews');
        })
       );
    }

  show(reviewId): Observable<Review>{
    return this.http.get<Review>(this.url, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
         console.log(err);
         return throwError('Error getting reviews');
      })
     );
  }

  create(item: Review, restaurant: Restaurant, user: User): Observable<Review> {

    console.log("create review service ");

    // set default values
    item.restaurant = restaurant;
    item.enabled = true;
    item.isPublic = true;


    console.log("Tears in the rain : " + JSON.stringify(item));

    return this.http.post<Review>(environment.baseUrl + "api/reviews/", item, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KaBOOM');
      })
    );
  }

  update(editedReview: Review) {
     return this.http.put<Review>(`${this.url}${editedReview.id}`, editedReview, this.getHttpOptions())
       .pipe( catchError((err:any) => {
        console.log(err);
        return throwError('Error updating todo in service');
       })
     );
  }

  destroy(id: number) {
   return this.http.delete(`${this.url}${id}`, this.getHttpOptions())
   .pipe(
     catchError((err:any) => {
        console.log(err);
        return throwError('Error deleting review in service');
     }));
}

private getHttpOptions(): object {
  const credentials = this.auth.getCredentials();
  const httpOptions = {
    headers: new HttpHeaders({
      Authorization: `Basic ${credentials}`,
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return httpOptions
}


}
