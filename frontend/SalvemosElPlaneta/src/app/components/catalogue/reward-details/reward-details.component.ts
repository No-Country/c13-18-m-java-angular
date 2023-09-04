import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RewardDTO } from 'src/app/models/reward-dto';
import { CatalogueService } from 'src/app/services/catalogue.service';

@Component({
  selector: 'app-reward-details',
  templateUrl: './reward-details.component.html',
  styleUrls: ['./reward-details.component.css']
})
export class RewardDetailsComponent implements OnInit {

  id=''
  reward!:RewardDTO

  constructor(
    private actRoute:ActivatedRoute,
    private catalService:CatalogueService,
  ){}

  ngOnInit(): void {
    this.actRoute.params.subscribe(
      (params)=>{
        this.id=params['id'];
      }
    )
    this.catalService.getById(this.id).subscribe({
      next:(resp:RewardDTO)=>{
        resp = this.reward;
      },
    })
  }

}
