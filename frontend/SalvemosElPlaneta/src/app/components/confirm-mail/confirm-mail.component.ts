import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmMailService } from 'src/app/services/confirm-mail.service';


@Component({
  selector: 'app-confirm-mail',
  templateUrl: './confirm-mail.component.html',
  styleUrls: ['./confirm-mail.component.css']
})
export class ConfirmMailComponent implements OnInit {

  token=''
  sended=false

  constructor(
    private actRoute:ActivatedRoute,
    private confirm:ConfirmMailService,
    private routes:Router
  ){}

  ngOnInit(): void {
    this.actRoute.params.subscribe(
      (params)=>{
        this.token=params['token'];
      }
    )
    this.confirm.activate({token:this.token}).subscribe(
      {
        next:()=>{
          this.sended=true;
        },
        error:(error:any)=>{
          console.log(error.message);
        },
        complete:()=>{
          this.isSended(this.sended);
        }
      }
    )
  }

  isSended(sended:boolean){
    if (sended){
      this.routes.navigate(['login']);
    }
    
  }
}
