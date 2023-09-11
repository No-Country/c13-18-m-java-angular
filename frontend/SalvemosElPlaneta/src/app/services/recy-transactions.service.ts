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

  recyTransactions():Observable<any>{
    return this.http.get(this.baseUrl + 'transactions');
  }

  recyTransactionByUser(id:number):Observable<any>{
    return this.http.get(this.baseUrl + 'transactions/user/' + id);
  }
}
