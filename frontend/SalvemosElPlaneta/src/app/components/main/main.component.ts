import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit,OnDestroy {

  isLogged!:boolean;
  user!:User;
  userPoints$!: Observable<number>;
  constructor(
    private authService:LoginService,
    ){}
    
    
    ngOnInit():void{
      this.authService.getCurrentUser().subscribe({
        next:(user)=> {
          if(user){
            this.user = user
            this.isLogged=true;
          }
          
        
          
      }
      
    });
    
    
    this.userPoints$ = this.authService.getUserPoints();
    
    
  }
  ngOnDestroy(): void {
    
  }
  
}