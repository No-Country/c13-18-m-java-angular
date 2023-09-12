import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecyTransactionsService {

  baseUrl = environment.apiUrl

  constructor(
    private http:HttpClient
  ) {}

  recyTransactionByUser(id:number,page:number,size:number):Observable<any>{
    return this.http.get(`${this.baseUrl}transactions/user/${id}?page=${page}&size=${size}`);
  }
}
