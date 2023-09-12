import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecyclingInfoService {

  constructor(private http:HttpClient) { }
  baseUrl= environment.apiUrl

  getAll():Observable<any>{
    return this.http.get(`${this.baseUrl}info`);
  }

}
