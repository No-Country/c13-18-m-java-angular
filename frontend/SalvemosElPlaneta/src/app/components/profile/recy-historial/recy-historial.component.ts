import { Component, Input, OnInit } from '@angular/core';
import { RecyTransaction } from 'src/app/models/recy-transaction';
import { RecyTransactionsService } from 'src/app/services/recy-transactions.service';

@Component({
  selector: 'app-recy-historial',
  templateUrl: './recy-historial.component.html',
  styleUrls: ['./recy-historial.component.css']
})
export class RecyHistorialComponent implements OnInit{

  recycles!:RecyTransaction[];
  @Input()userId!:number;
  currentPage:number = 0;
  page:any ;
  totalPages!:number[]
  pageSize:number = 2

  constructor (
    private recyHistServ:RecyTransactionsService
  ) {}

  ngOnInit(): void {
    this.getRecyTransactions(this.userId);
  }

  getRecyTransactions(userId:number,page:number=0,size:number=2){
    this.recyHistServ.recyTransactionByUser(userId,page,size).subscribe({
      next:(resp)=>{
        this.page = resp;
        this.recycles = resp.content;
        this.totalPages = Array(resp.totalPages).fill(0);
      },
      error:()=>{

      },
      complete:()=>{
        
      }
    });
  }

  numPerPage(){
    
  }

  pagination(currentPage:number){
    this.currentPage = currentPage;
    this.getRecyTransactions(this.userId,currentPage,this.pageSize);
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
