import { Component, OnInit } from '@angular/core';
import { RewardDTO } from 'src/app/models/reward-dto';
import { CatalogueService } from 'src/app/services/catalogue.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-catalogue',
  templateUrl: './catalogue.component.html',
  styleUrls: ['./catalogue.component.css']
})
export class CatalogueComponent implements OnInit{
  
  rewards!:RewardDTO[];
  currentUser!:any;
  currentPage=1;
  itemsPerPage:number = 4;
  rewardsArray:RewardDTO[]=[]
  constructor(private service:CatalogueService,private authService:LoginService){}
  ngOnInit(): void {

    this.getRewards();
    this.authService.getCurrentUser().subscribe({
      next:(user)=>{
        this.currentUser = user
      }
    });

  }

  getRewards(){
    this.service.getAllRewards().subscribe({
      next:(response)=>{
        this.rewards = response;
        this.rewardsArray = this.rewards;
      }
    });
  }
  get totalPages(): number {
    return Math.ceil(this.rewardsArray.length / this.itemsPerPage);
  }

  getItemsForCurrentPage(): any[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.rewardsArray.slice(startIndex, endIndex);
  }

  handleRedeem(data:RewardDTO){
    console.log(data.id)
    // this.redeemReward(data.id,this.currentUser.id);
  }

  prevPage():void{
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }
  nextPage():void{
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
    }
  }

  redeemReward(rewardId:number,userId:number){
    this.service.redeem(rewardId,userId).subscribe({
      next:(response:any)=>{
        console.log("canjeado")
        
      },
      error:(err:any)=>{
        console.log(err)
      },
      complete:()=>{
        this.getRewards();
      }
    });
  }

}
