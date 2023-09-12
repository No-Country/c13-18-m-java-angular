import { Component, Input } from '@angular/core';
import { RecyclingInfo } from 'src/app/models/recycling-info';

@Component({
  selector: 'app-article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.css']
})
export class ArticleCardComponent {

  @Input() post!:RecyclingInfo;

  formatContent(str:string):string{
    const paragraphs = str.split('\n')
    return paragraphs[0];
  }


  slugTitle(str:string):string{
    return str.replaceAll(" ","-").replaceAll(":","-").toLowerCase()
  }
}
