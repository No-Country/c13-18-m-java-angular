import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  url = '';

  constructor(private http: HttpClient,
    private router: Router) {}
  
  register({email, password}: any){
    this.http.put(this.url + '/register', {email: email,password: password})
      .subscribe((resp: any) => {
      console.log(resp);
  }
)}
}
