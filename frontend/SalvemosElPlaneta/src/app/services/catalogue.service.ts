import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RewardDTO } from '../models/reward-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {
  redeem(rewardId: number, userId: number):Observable<any> {
    return this.http.post(`${this.baseUrl}redeem/reward`,{rewardId,userId});
  }

  private baseUrl=environment.apiUrl;
  constructor(private http:HttpClient) { }


  getAllRewards():Observable<RewardDTO[]>{
    return this.http.get<RewardDTO[]>(`${this.baseUrl}rewards`);
  }

  getById(id:string):Observable<RewardDTO>{
    return this.http.get<RewardDTO>(this.baseUrl + "rewards/" + id);
  }
}
