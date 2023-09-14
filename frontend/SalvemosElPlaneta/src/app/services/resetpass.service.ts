import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ResetpassService {

  url=environment.authUrl

  constructor(
    private http:HttpClient
  ) { }

  request(mail:any):Observable<any>{
    return this.http.post(this.url + 'auth/password-reset/request', mail);
  }

  reset(token:string,newPassword:string):Observable<any>{
    return this.http.post(this.url + 'auth/password-reset/reset?token=' + token + '&newPassword=' + newPassword, null)
  }
}
