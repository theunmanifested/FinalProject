import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Restaurant } from '../models/restaurant';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class RestaurantService {

  private url = environment.baseUrl + 'api/restaurants/';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  show(id: string): Observable<Restaurant> {

    return this.http.get<Restaurant>(environment.baseUrl + "api/pub/restaurants/" + id)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }


// // restaurant dashboard continue
  create(newRest: Restaurant) {
    return this.http.post<Restaurant>(this.url, newRest, this.getHttpOptions())
    .pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError('Error creating restaurant');
      })
    );
  }

  destroy(id: number) {
    return this.http.delete<Restaurant>(`${this.url}/${id}`, this.getHttpOptions())

    .pipe(
      catchError((err:any) => {
         console.log(err);
         return throwError('Error deleting restaurant in service');
      }));
 }


  search(term: string): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(environment.baseUrl + "api/pub/restaurants/bysearch/" + term).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error in index()' + err);
      })
    );
  }

  private getHttpOptions() {
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }
}
