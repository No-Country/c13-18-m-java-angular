import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RewardTransactionsService {

  baseUrl = environment.apiUrl

  constructor(
    private http:HttpClient
  ) {}

  transactionByUser(id:number,page:number=0,size:number=5):Observable<any>{
    return this.http.get(`${this.baseUrl}rewards/transactions/user/${id}?page=${page}&size=${size}`);
  }

}
