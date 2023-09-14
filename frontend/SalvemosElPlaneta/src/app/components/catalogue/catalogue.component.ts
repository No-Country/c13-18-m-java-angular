import { Component, OnInit } from '@angular/core';
import { RewardDTO } from 'src/app/models/reward-dto';
import { User } from 'src/app/models/user';
import { CatalogueService } from 'src/app/services/catalogue.service';
import { LoginService } from 'src/app/services/login.service';
import { Location } from '@angular/common'

@Component({
  selector: 'app-catalogue',
  templateUrl: './catalogue.component.html',
  styleUrls: ['./catalogue.component.css']
})
export class CatalogueComponent implements OnInit{
  
  rewards!:RewardDTO[];
  currentUser!:User;
  currentPage=1;
  itemsPerPage:number = 4;
  rewardsArray:RewardDTO[]=[]
  isLoading=false;
  constructor(private service:CatalogueService,private authService:LoginService,private location: Location){}
  ngOnInit(): void {

    this.getRewards();
    this.authService.getCurrentUser().subscribe({
      next:(user)=>{
        if (user) {
          this.currentUser = user
        }
      }
    });

  }

  getRewards(){
    this.isLoading = true;
    
    this.service.getAllRewards().subscribe({
      next:(response)=>{
        this.rewards = response;
        this.rewardsArray = this.rewards;
        this.isLoading=false;
      },error:(err)=> {
          this.isLoading=false;
      },
      complete:()=>{
        this.isLoading=false;
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

  back(): void {
    this.location.back()
  }

}
