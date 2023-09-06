import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  isLogged!:boolean;
  user!:any;
  constructor(
    private authService:LoginService,
    ){}


  ngOnInit():void{
    this.authService.getCurrentUser().subscribe({
      next:(user)=> {
        
        this.user = user
        
      }
    });
    this.isLogged = this.authService.isLogged();
  }
}
