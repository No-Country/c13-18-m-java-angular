import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { RecyTransactionsService } from 'src/app/services/recy-transactions.service';

@Component({
  selector: 'app-recy-historial',
  templateUrl: './recy-historial.component.html',
  styleUrls: ['./recy-historial.component.css']
})
export class RecyHistorialComponent implements OnInit{

  user!:User;
  userId!:number;

  constructor (
    private authService:LoginService,
    private recyHistServ:RecyTransactionsService

  ) {}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe({
      next:(user)=> {
        if (user) {
          this.user = user
          this.userId = user.id
        };
      }
    });
  }

  getRecyTransactions(){
    this.recyHistServ.recyTransactionByUser(this.userId).subscribe({
      next:(resp)=>{
        console.log(resp);
      },
      error:()=>{

      },
      complete:()=>{
        
      }
    });
  }

}
