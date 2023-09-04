import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RecyclingPoint } from '../models/recycling-point';

@Injectable({
  providedIn: 'root'
})
export class RecyPointsService {

  baseUrl=environment.apiUrl

  constructor(
    private http:HttpClient
  ) { }

  points():Observable<RecyclingPoint[]>{
    return this.http.get<RecyclingPoint[]>(this.baseUrl + "RecyclingPoint");
  }

  getById(id:string):Observable<RecyclingPoint>{
    return this.http.get<RecyclingPoint>(this.baseUrl + "RecyclingPoint/" + id);
  }

  filterByTime(openingTime:string,closingTime:string):Observable<RecyclingPoint[]>{
    return this.http.get<RecyclingPoint[]>(this.baseUrl + 'RecyclingPoint/filterByTime?openingTime=' + openingTime + '&closingTime=' + closingTime);
  }
}
