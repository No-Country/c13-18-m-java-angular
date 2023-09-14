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

  filteredRecyPoints!:RecyclingPoint[];
  recyPoints!:RecyclingPoint[];
  nameFilter!:string

  constructor(
    private recyServ:RecyPointsService,
  ){}
  
  ngOnInit(): void {
    this.recyServ.points().subscribe({
      next:(resp)=>{
        this.filteredRecyPoints = resp;
        this.recyPoints = resp;
      }
    });
  }

  formatTime(time:string):string{
    const tf = time.split(":")
    return tf[0].concat(":"+tf[1]);
  }

  nameSearch(q:string){
    if (!q.length) {
      this.filteredRecyPoints = this.recyPoints;
    }
    this.filteredRecyPoints = this.filteredRecyPoints.filter(
      (point:RecyclingPoint) =>point.address.toLowerCase().includes(q.toLowerCase())||
                    point.openingTime.toLowerCase().includes(q.toLowerCase())||
                    point.closingTime.toLowerCase().includes(q.toLowerCase())||
                    point.comuna.toString().includes(q.toLowerCase())
      );
  }

}
