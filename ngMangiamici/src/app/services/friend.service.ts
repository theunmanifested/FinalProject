import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { Friend } from '../models/friend';

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  private url = environment.baseUrl + 'api/friends/';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  getUser(username: string): Observable<User> {
    return this.http.get<User>(this.url + username, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error in index()' + err);
      })
    );
  }

  getLoggedInUser(): Observable<User> {
    return this.http.get<User>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error in index()' + err);
      })
    );
  }

  getFriends(user: User): Observable<Friend[]> {
    return this.http.get<Friend[]>(this.url, this.getHttpOptions()).pipe(
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
