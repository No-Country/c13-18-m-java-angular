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
  pageSize:number = 5
  sizes = [5,10,15,20]
  isIncorrect = false
  totalElements!:number

  constructor(
    private rewardTransServ:RewardTransactionsService,
  ){}

  ngOnInit(): void {
    this.getTransactions(this.userId,this.currentPage,this.pageSize);
  }

  getTransactions(userId:number,page:number,size:number){
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

  setSize(size:number){
    if (this.page){
      if (size > this.page.totalElements) {
        this.pageSize = this.page.totalElements;
        this.isIncorrect = true;
      } else {
        this.pageSize = size;
        this.isIncorrect = false;
      }
    this.getTransactions(this.userId,this.currentPage,this.pageSize);
    }
  }

  pagination(currentPage:number){
    this.currentPage = currentPage;
    this.getTransactions(this.userId,this.currentPage,this.pageSize);
  }

  prevPage(){
    this.currentPage = this.currentPage > 0? --this.currentPage: this.currentPage = 0;
    this.pagination(this.currentPage);
  }

  nextPage(){
    this.currentPage = this.currentPage < (this.totalPages.length-1)? ++this.currentPage: this.currentPage = (this.totalPages.length-1);
    this.pagination(this.currentPage);
  }


}
