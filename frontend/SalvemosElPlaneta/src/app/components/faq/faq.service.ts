import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FaqService {

  constructor(private http:HttpClient) { }

  getFaq():Observable<any>{
    return this.http.get("/assets/data/faq.json")
  }
}
