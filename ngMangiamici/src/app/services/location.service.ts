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
export class LocationService {

  private url = environment.baseUrl + 'api/locations/';


  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }
}
