import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";
import {Geocoding} from "../../model/geocoding";
import {Weather} from "../../model/weather";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) { }

  search(name : string): Observable<Geocoding[]>{
    const options = {params : new HttpParams().set('city', name)}
    return this.http.get<Geocoding[]>('http://localhost:8080/api/weather/city', options).pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  getWeather(latitude: number, longitude: number): Observable<Weather>{
    const options = {params : new HttpParams().set('latitude', latitude).set('longitude', longitude)}
    return this.http.get<Weather>('http://localhost:8080/api/weather', options).pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

}
