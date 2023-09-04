import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recyclable } from '../models/recyclable';

@Injectable({
  providedIn: 'root'
})
export class RecyclableService {

  baseUrl=environment.apiUrl

  constructor(
    private http:HttpClient
  ){}

  recyclable():Observable<Recyclable>{
    return this.http.get<Recyclable>(this.baseUrl + 'recyclable');
  }

  recyclableById(id:string):Observable<Recyclable>{
    return this.http.get<Recyclable>(this.baseUrl + 'recyclable' + id);
  }

  recycla(request:Recyclable):Observable<Recyclable>{
    return this.http.post<Recyclable>(this.baseUrl + 'recyclable', request);
  }

}
