import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfirmMailService {

  url=environment.authUrl

  constructor(
    private http:HttpClient
  ) { }

  activate(token:any):Observable<any>{
    return this.http.post(this.url + 'token', token);
  }
}
