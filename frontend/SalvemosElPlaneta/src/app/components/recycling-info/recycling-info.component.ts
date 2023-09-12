import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { RecyclingInfo } from 'src/app/models/recycling-info';
import { RecyclingInfoService } from './recycling-info.service';

@Component({
  selector: 'app-recycling-info',
  templateUrl: './recycling-info.component.html',
  styleUrls: ['./recycling-info.component.css']
})
export class RecyclingInfoComponent implements OnInit,OnDestroy {
  
  
  recyclingInfo$!:Subscription;
  recyclingInfo!:RecyclingInfo[];
  filteredArticles!:RecyclingInfo[];
  constructor(private service:RecyclingInfoService){}
  ngOnInit(): void {
    this.recyclingInfo$ = this.service.getAll().subscribe({
      next:(response:RecyclingInfo[])=>{
        this.recyclingInfo = response
        this.filteredArticles = response
      }
    });
  }
  

  ngOnDestroy(): void {
    if(this.recyclingInfo$){
      this.recyclingInfo$.unsubscribe()
    }
  }


  formatContent(str:string):string[]{
    return str.split('\n');
  }
  selectedTag:string[]=[];

  selectTag(tag: string): boolean {
    const index = this.selectedTag.indexOf(tag);
    if (index !== -1) {
      this.selectedTag = this.selectedTag.filter(item => item !== tag);
    } else {
      this.selectedTag.push(tag);
    }
  
    console.log(this.selectedTag);
    this.filteredArticles = this.filterArticles(this.recyclingInfo);
    return this.selectedTag.includes(tag);
  }

  filterArticles(articles: RecyclingInfo[]): any[] {
    return articles.filter(article => this.selectedTag.includes(article.tag.toLowerCase()));
  }

}
