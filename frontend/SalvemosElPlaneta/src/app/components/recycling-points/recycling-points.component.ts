import { Component, OnInit } from '@angular/core';
import { RecyPointsService } from 'src/app/services/recy-points.service';

@Component({
  selector: 'app-recycling-points',
  templateUrl: './recycling-points.component.html',
  styleUrls: ['./recycling-points.component.css']
})
export class RecyclingPointsComponent implements OnInit {



  constructor(
    private recyServ:RecyPointsService,
  ){}
  
  ngOnInit(): void {
    this.recyServ.points().subscribe({
      next:(resp)=>{
        
      },
      error:()=>{

      },
      complete:()=>{

      }
    });
  }

}
