import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RewardDTO } from 'src/app/models/reward-dto';
import { CatalogueService } from 'src/app/services/catalogue.service';
import { LoginService } from 'src/app/services/login.service';
import { Clipboard } from '@angular/cdk/clipboard';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Location } from '@angular/common'
@Component({
  selector: 'app-reward-details',
  templateUrl: './reward-details.component.html',
  styleUrls: ['./reward-details.component.css']
})
export class RewardDetailsComponent implements OnInit {

  reward!:RewardDTO
  rewardId!:string;
  userId!:number;
  showModal=false;
  copied=false;
  voucherCode:string="";
  progressWidth="100%";

  constructor(
    private actRoute:ActivatedRoute,
    private service:CatalogueService,
    private authService:LoginService,
    private toastr:ToastrService,
    private location: Location
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
    if(!this.userId){
      this.router.navigate(["/login"])
    }
    this.service.redeem(parseInt(this.rewardId),this.userId).subscribe({
      next:(response)=>{
        this.voucherCode = response.voucher
        this.showModal = !this.showModal
        
      },
      error:(err:HttpErrorResponse)=>{
        this.toastr.error(err.error.message,"UPS!",{
          timeOut:3000,
          progressBar:true,
          progressAnimation:'decreasing',
        
        })
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
    location.reload()
    this.router.navigate(['/catalogo']);
  }
  back(): void {
    this.location.back()
  }
}
