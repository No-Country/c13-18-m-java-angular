import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { RedeemCode } from '../models/redeem-code';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RedeemCodeService {

  url = environment.apiUrl

  constructor(
    private http:HttpClient,
  ) { }

  redeemCode(request:RedeemCode):Observable<any>{
    return this.http.post(this.url + 'redeem', request);
  }

  validateCode(code:string):Observable<any>{
    return this.http.post(this.url + 'details?code=' + code, null)
  }
}
