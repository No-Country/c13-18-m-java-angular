import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { RedeemCode } from '../models/redeem-code';
import { Observable } from 'rxjs';
import { Recyclables } from '../models/recyclables';
import { CodeDetails } from '../models/code-details';

@Injectable({
  providedIn: 'root'
})
export class RedeemCodeService {

  url = environment.apiUrl

  constructor(
    private http:HttpClient,
  ) { }

  redeemCode(request:RedeemCode):Observable<RedeemCode>{
    return this.http.post<RedeemCode>(this.url + 'redeem', request);
  }

  validateCode(code:string):Observable<CodeDetails>{
    return this.http.get<CodeDetails>(this.url + 'details?code=' + code);
  }

  recyclable(request:Recyclables):Observable<any>{
    return this.http.post(this.url + 'recycle', request);
  }
}
