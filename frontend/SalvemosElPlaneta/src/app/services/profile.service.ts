import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProfileRequest } from '../models/profile-request';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  url=environment.apiUrl

  constructor(
    private http:HttpClient,
  ) { }

  userModif(id:string, request:ProfileRequest):Observable<ProfileRequest>{
    return this.http.put<ProfileRequest>(this.url + 'users/' + id, request);
  }

}
