import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { RewardDTO } from '../models/reward-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RewardsService {

  baseUrl=environment.apiUrl

  constructor(
    private http:HttpClient
  ) { }

  allRewards():Observable<RewardDTO[]>{
    return this.http.get<RewardDTO[]>(this.baseUrl + 'rewards');
  }

  rewardById(id:string):Observable<RewardDTO>{
    return this.http.get<RewardDTO>(this.baseUrl + 'rewards' + id);
  }

  rewardByName(name:string):Observable<RewardDTO>{
    return this.http.get<RewardDTO>(this.baseUrl + 'rewards/name/' + name);
  }

  rewards(request:RewardDTO):Observable<RewardDTO>{
    return this.http.post<RewardDTO>(this.baseUrl + 'rewards', request);
  }

}
