import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { RewardDTO } from 'src/app/models/reward-dto';

@Component({
  selector: 'app-reward-card',
  templateUrl: './reward-card.component.html',
  styleUrls: ['./reward-card.component.css']
})
export class RewardCardComponent{

  @Input() reward:RewardDTO = {} as RewardDTO;
  @Input() currentUser:any = {};

  @Output() redeem = new EventEmitter<RewardDTO>();


  onRedeem(reward:RewardDTO){
    this.redeem.emit(reward)
  }
}
