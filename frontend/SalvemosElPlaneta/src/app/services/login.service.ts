import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { LoginRequest } from '../models/login-request';
import { BehaviorSubject, Observable, catchError, switchMap, tap, throwError } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private currentUserSubject: BehaviorSubject<any> = new BehaviorSubject<any>({})
  url = environment.authUrl

  constructor(
    private http: HttpClient,
    private cookie: CookieService,
    private router: Router) {}

  setCurrentUser(user:any){
    this.currentUserSubject.next(user);
  }

  getCurrentUser(){
    return this.currentUserSubject.asObservable();
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
      tap((user: any) => {
        this.setCurrentUser(user);
      }),
      catchError((err: any) => {
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
    this.setCurrentUser({});
  }
}