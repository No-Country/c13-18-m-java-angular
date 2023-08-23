import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = '';

  constructor(
    private router: Router) {}

// login({email, password}: any) {
// this.http.put(this.url + '/login', {email: email,password: password})
// .subscribe((resp: any) => {
// console.log(resp);
// if (resp) {
//   sessionStorage.setItem('token', resp);
// }
// //this.router.navigate(['sobre-mi']);
// location.reload();
// });
// }

// logout() {
// console.log("logout funciona");
// sessionStorage.removeItem('token');
// location.reload();
// }

}

// private http: HttpClient
