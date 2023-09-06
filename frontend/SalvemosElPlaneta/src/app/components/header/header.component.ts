import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userAct: any
  logged: boolean = false

  constructor(
    private logservice:LoginService
    )
    {}

    ngOnInit(): void {
      this.logservice.initializeCurrentUser();
      this.logservice.getCurrentUser().subscribe(
        {
          next:(response:any)=>{
            this.userAct=response
            this.logged=this.logservice.isLogged();
            }
        }
        )
    }

    logout():void {
      this.logservice.logout();
      this.logged = false;
      location.reload()
    }
}
