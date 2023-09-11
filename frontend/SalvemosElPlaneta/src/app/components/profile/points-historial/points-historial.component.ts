import { Component, OnInit } from '@angular/core';
import { RewardTransaction } from 'src/app/models/reward-transaction';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { RewardTransactionsService } from 'src/app/services/reward-transactions.service';

@Component({
  selector: 'app-points-historial',
  templateUrl: './points-historial.component.html',
  styleUrls: ['./points-historial.component.css']
})
export class PointsHistorialComponent implements OnInit {

  transactions!:RewardTransaction[]
  user!:User;
  userId!:number;

  constructor(
    private rewardTransServ:RewardTransactionsService,
    private authService:LoginService
  ){}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe({
      next:(user)=> {
        if (user) {
          this.user = user
          this.userId = user.id
        };
      }
    });
    this.getTransactions();
  }

  getTransactions(){
    this.rewardTransServ.transactionByUser(this.user.id).subscribe({
      next:(resp)=>{
        this.transactions = resp.content;
        console.log(this.transactions);
      },
      error:()=>{

      },
      complete:()=>{

      }
    });
    
  }

}
