import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecyclingInfo } from 'src/app/models/recycling-info';
import { RecyclableInfoService } from 'src/app/services/recyclable-info.service';
import { RecyclingInfoService } from '../recycling-info.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-recycling-info-detail',
  templateUrl: './recycling-info-detail.component.html',
  styleUrls: ['./recycling-info-detail.component.css']
})
export class RecyclingInfoDetailComponent implements OnInit{
  
  constructor(private activatedRoute:ActivatedRoute,private service:RecyclingInfoService,private location:Location){}
  title!:string;
  articleId!:number;
  article!:RecyclingInfo;
  ngOnInit(): void {

    this.activatedRoute.params.subscribe(
      (params)=>{
      this.title = params['title']
      this.articleId = params['id']

    })

    this.service.getById(this.articleId).subscribe({
      next:(response)=>{
        this.article = response
      }
    })

    
  }
  
  formatContent(str:string):string[]{
    return str.split('\n');
  }
  
  
  back(){
    this.location.back()
  }

}
