import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { RedeemCode } from '../models/redeem-code';
import { Observable } from 'rxjs';
import { Recyclables } from '../models/recyclables';
import { PointsDetails } from '../models/points-details';

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

  validateCode(code:string):Observable<PointsDetails>{
    return this.http.post<PointsDetails>(this.url + 'details?code=' + code, null);
  }

  recyclable(request:Recyclables):Observable<any>{
    return this.http.post(this.url + 'recycle', request);
  }
}
