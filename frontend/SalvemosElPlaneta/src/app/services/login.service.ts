import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { LoginRequest } from '../models/login-request';
import { BehaviorSubject, Observable, catchError, switchMap, tap, throwError } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private currentUserSubject: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null)
  private userPointsSubject = new BehaviorSubject<number>(0);
  url = environment.authUrl


  constructor(
    private http: HttpClient,
    private cookie: CookieService) {}

    initializeCurrentUser(): void {
      const tokenKey = 'token';
      const usernameKey = 'sub';
      
      if (this.cookie.check(tokenKey)) {
        const token = this.cookie.get(tokenKey);
        const decoded: any = jwt_decode(token);
        const username = decoded[usernameKey];
  
        this.getUserByUsername(username).subscribe({
          next: (user: User) => {
            this.setCurrentUser(user);
            this.setUserPoints(user.points)
          },
          error: () => {
  
            this.cookie.deleteAll();
          }
        });
      }
    }

  setCurrentUser(user:User | null){
    this.currentUserSubject.next(user);
  }

  getCurrentUser(){
    return this.currentUserSubject.asObservable();
  }
  setUserPoints(points: number): void {
    this.userPointsSubject.next(points);
  }

  getUserPoints(): Observable<number> {
    return this.userPointsSubject.asObservable();
  }

  isLogged():boolean{
    const token = this.cookie.get("token");
    return  token !== null && token !== '';
  }

  login(request: LoginRequest):Observable<any>{
    return this.http.post(this.url + 'auth/login', request).pipe(
      switchMap((res: any) => {
        this.cookie.set('token', res.token);
        const decoded: any = jwt_decode(res.token);
        return this.getUserByUsername(decoded.sub);
      }),
      tap((user: User) => {
        this.setCurrentUser(user);
        this.setUserPoints(user.points)
      }),
      catchError(() => {
        this.cookie.delete('token', '/');
        return throwError(()=> new Error('Usuario o contraseña incorrectos.'));
      })
    )
  }

  getUserByUsername(username:string):Observable<any>{
    return this.http.get(this.url + 'api/v1/users/username/' + username);
  }

  logout(){
    this.cookie.deleteAll();
    this.setCurrentUser(null);
  }
}