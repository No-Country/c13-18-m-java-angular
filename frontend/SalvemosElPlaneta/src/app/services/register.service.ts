import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { RegisterRequest } from '../models/register-request';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  url = environment.authUrl

  constructor(private http: HttpClient,
    private router: Router) {}
  
  register(request: RegisterRequest):Observable<any>{
    return this.http.post(this.url + 'auth/register', request)
  }
}
