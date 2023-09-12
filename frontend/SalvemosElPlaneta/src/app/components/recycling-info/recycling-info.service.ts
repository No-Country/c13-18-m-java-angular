import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RecyclableInfo } from 'src/app/models/recyclable-info';
import { RecyclingInfo } from 'src/app/models/recycling-info';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecyclingInfoService {

  constructor(private http:HttpClient) { }
  baseUrl= environment.apiUrl

  getAll():Observable<RecyclingInfo[]>{
    return this.http.get<RecyclingInfo[]>(`${this.baseUrl}info`);
  }

  getById(id:number):Observable<RecyclingInfo>{
    return this.http.get<RecyclingInfo>(`${this.baseUrl}info/${id}`);
  }

}
