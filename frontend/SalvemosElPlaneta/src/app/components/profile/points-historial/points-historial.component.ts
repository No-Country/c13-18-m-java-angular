import { Component, Input, OnInit } from '@angular/core';
import { RewardTransaction } from 'src/app/models/reward-transaction';
import { RewardTransactionsService } from 'src/app/services/reward-transactions.service';

@Component({
  selector: 'app-points-historial',
  templateUrl: './points-historial.component.html',
  styleUrls: ['./points-historial.component.css']
})
export class PointsHistorialComponent implements OnInit {

  transactions!:RewardTransaction[]
  @Input()userId!:number;
  currentPage:number = 0;
  page:any ;
  totalPages!:number[]
  pageSize:number = 2

  constructor(
    private rewardTransServ:RewardTransactionsService,
  ){}

  ngOnInit(): void {
    this.getTransactions(this.userId);
  }

  getTransactions(userId:number,page:number=0,size:number=2){
    this.rewardTransServ.transactionByUser(userId,page,size).subscribe({
      next:(resp)=>{
        this.page = resp
        this.transactions = resp.content;
        this.totalPages = Array(resp.totalPages).fill(0);
      },
      error:()=>{

      },
      complete:()=>{

      }
    });
    
  }

  pagination(currentPage:number){
    this.currentPage = currentPage;
    this.getTransactions(this.userId,currentPage,this.pageSize);
  }

  prevPage(){
    this.currentPage = this.currentPage > 0? --this.currentPage: this.currentPage = 0;
    console.log(this.currentPage);
    this.pagination(this.currentPage);
  }

  nextPage(){
    this.currentPage = this.currentPage < (this.totalPages.length-1)? ++this.currentPage: this.currentPage = (this.totalPages.length-1);
    console.log(this.currentPage);
    this.pagination(this.currentPage);
  }


}
