import { query } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { RecyclingPoint } from 'src/app/models/recycling-point';
import { RecyPointsService } from 'src/app/services/recy-points.service';

@Component({
  selector: 'app-recycling-points',
  templateUrl: './recycling-points.component.html',
  styleUrls: ['./recycling-points.component.css']
})
export class RecyclingPointsComponent implements OnInit {

  list!:RecyclingPoint[];
  nameFilter!:string

  constructor(
    private recyServ:RecyPointsService,
  ){}
  
  ngOnInit(): void {
    this.recyServ.points().subscribe({
      next:(resp)=>{
        this.list = resp;
      },
      error:()=>{

      },
      complete:()=>{

      }
    });
  }

  formatTime(time:string):string{
    const tf = time.split(":")
    return tf[0].concat(":"+tf[1]);
  }

  nameSearch(q:string){
    this.list = this.list.filter(
      (point:RecyclingPoint) =>point.address.toLowerCase().includes(q.toLowerCase())||
                    point.openingTime.toLowerCase().includes(q.toLowerCase())||
                    point.closingTime.toLowerCase().includes(q.toLowerCase())||
                    point.comuna.toString().includes(q.toLowerCase())
      );
  }

}
