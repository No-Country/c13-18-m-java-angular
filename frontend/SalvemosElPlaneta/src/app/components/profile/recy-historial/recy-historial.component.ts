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
  pageSize:number = 5
  sizes = [5,10,15,20]
  isIncorrect = false
  totalElements!:number


  constructor (
    private recyHistServ:RecyTransactionsService
  ) {}

  ngOnInit(): void {
    this.getRecyTransactions(this.userId,this.currentPage,this.pageSize);
  }

  getRecyTransactions(userId:number,page:number,size:number){
    this.recyHistServ.recyTransactionByUser(userId,page,size).subscribe({
      next:(resp)=>{
        this.page = resp;
        this.recycles = resp.content;
        this.totalPages = Array(resp.totalPages).fill(0);
        this.totalElements = resp.totalElements;
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
    this.getRecyTransactions(this.userId,this.currentPage,this.pageSize);
    }
  }

  pagination(currentPage:number){
    this.currentPage = currentPage;
    this.getRecyTransactions(this.userId,this.currentPage,this.pageSize);
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
