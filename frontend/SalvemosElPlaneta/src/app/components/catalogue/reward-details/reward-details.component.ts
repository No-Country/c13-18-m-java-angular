import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RewardDTO } from 'src/app/models/reward-dto';
import { CatalogueService } from 'src/app/services/catalogue.service';
import { LoginService } from 'src/app/services/login.service';
import {Clipboard} from '@angular/cdk/clipboard';

@Component({
  selector: 'app-reward-details',
  templateUrl: './reward-details.component.html',
  styleUrls: ['./reward-details.component.css']
})
export class RewardDetailsComponent implements OnInit {

  reward!:RewardDTO
  rewardId!:string;
  userId!:string;
  showModal=false;
  copied=false;
  voucherCode:string="";
  progressWidth="100%";

  constructor(
    private actRoute:ActivatedRoute,
    private service:CatalogueService,
    private authService:LoginService,
    private clipboard:Clipboard
  ){}
  router = inject(Router)
  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe({
      next:(user)=>{
        if(user!= null){

          this.userId = user.id
        }
      }
    });
    this.actRoute.params.subscribe(
      (params)=>{
        this.rewardId=!Number.isNaN(parseInt(this.rewardId))?"0":params['id'];
      }
    )
    if (this.rewardId!=="0") {
      this.service.getById(this.rewardId).subscribe({
        next:(resp:RewardDTO)=>{
          this.reward = resp;
        },
      })
    }
    
  }
  redeemReward():void{
    this.service.redeem(parseInt(this.rewardId),parseInt(this.userId)).subscribe({
      next:(response)=>{
        this.voucherCode = response.voucher
        this.showModal = !this.showModal
        
      },
      error:(err:any)=>{
        console.log(err)
      }
    });
  }


  copyCode(): void {
    navigator.clipboard.writeText(this.voucherCode).then(() => {
      this.copied = true;
  
      let step = 0;
      const interval = 30; 
      const totalSteps = 3000 / interval; 
  
      const progressInterval = setInterval(() => {
        step++;
        this.progressWidth = (((totalSteps - step) / totalSteps) * 100).toString() + '%';
  
        if (step >= totalSteps) {
          clearInterval(progressInterval);
          this.copied = false;
          this.progressWidth = '0%'; 
        }
      }, interval);
    }).catch(() => {
      console.error("Unable to copy text");
    });
  }
  closeDialog():void{
    this.router.navigate(['/catalogo']);
  }

}
