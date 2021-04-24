import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Review } from '../models/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private url = environment.baseUrl + 'api/reviews/';


  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }


  // methods

  index(): Observable<Review[]> {
    return this.http.get<Review[]>(this.url, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
         console.log(err);
         return throwError('Error getting todos');
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

  create(newReview: Review) {
    // FIXME not too sure if reviewText should be manipulated here
    newReview.reviewText = '';
    // newReview.  -- Add the remaining fields needed/wanted to create/add a new review
    return this.http.post<Review>(this.url, newReview, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError('Error creating review');
      })
    );
  }

  update(editedReview: Review) {
   return this.http.put<Review>(`${this.url}/${editedReview.id}`, editedReview, this.getHttpOptions())
   .pipe( catchError((err:any) => {
    console.log(err);
    return throwError('Error updating todo in service');
   })
   );
  }
  destroy(id: number) {
   return this.http.delete<Review>(`${this.url}/${id}`, this.getHttpOptions())
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
