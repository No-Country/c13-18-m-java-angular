import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RecyclableInfo } from '../models/recyclable-info';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RecyclableInfoService {

  baseUrl=environment.authUrl

  constructor(
    private http:HttpClient
  ){}

  info():Observable<RecyclableInfo>{
    return this.http.get<RecyclableInfo>(this.baseUrl + '/');
  }

  infoById(id:string):Observable<RecyclableInfo>{
    return this.http.get<RecyclableInfo>(this.baseUrl + '/' + id);
  }

  infoByTag(tag:string):Observable<RecyclableInfo>{
    return this.http.get<RecyclableInfo>(this.baseUrl + '/' + tag);
  }

  filter(request:RecyclableInfo):Observable<RecyclableInfo>{
    return this.http.post<RecyclableInfo>(this.baseUrl + '/', request);
  }
}
