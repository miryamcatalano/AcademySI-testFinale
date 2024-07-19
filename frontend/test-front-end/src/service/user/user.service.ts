import { Injectable } from '@angular/core';
import {RegisterRequest} from "../../model/registerRequest";
import {LoginRequest} from "../../model/loginRequest";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  registerRequest : RegisterRequest = new RegisterRequest();
  loginRequest : LoginRequest = new LoginRequest();

  constructor(private http: HttpClient) { }

  regUser(user: RegisterRequest) : Observable<void>{
    return this.http.post<void>('http://localhost:8080/api/user/', user).pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  loginUser(user: LoginRequest) : Observable<void>{
    return this.http.post<void>('http://localhost:8080/api/user/login', user).pipe(
      retry(3),
      catchError(err => {
        console.error("loginError", err);
        return throwError(err);
      }),
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
