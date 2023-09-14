import { Component, OnInit } from '@angular/core';
import { FaqService } from './faq.service';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit{
  
  faq!:any[]

  constructor(private service:FaqService){}
  ngOnInit(): void {

    this.service.getFaq().subscribe({
      next:(res)=>{
        this.faq = res
      }
    })

  }

  formatStr(str:string){
    return str.replaceAll(/\n/g, '<br>')
  }

}
