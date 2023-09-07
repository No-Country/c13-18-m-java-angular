import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileRequest } from 'src/app/models/profile-request';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  isLogged!:boolean;
  user!:any;
  formProfile!:FormGroup
  profileRequest!:ProfileRequest

  constructor(
    private authService:LoginService,
    private fb:FormBuilder,
    ){}


  ngOnInit():void{
    this.authService.getCurrentUser().subscribe({
      next:(user)=> {
        this.user = user;
      }
    });
    this.isLogged = this.authService.isLogged();
    this.crearForm();
  }

  crearForm(){
    this.formProfile = this.fb.group(
      {
        firstname:['',[Validators.required,Validators.minLength(2)]],
        lastname:['',[Validators.required,Validators.minLength(2)]],
        country: ['',[Validators.required,Validators.minLength(2)]],
      }
    );
  }

  get Username() {
    return this.formProfile.get('firstname')?.invalid && this.formProfile.get('firstname')?.touched;
  }

  get Lastname() {
    return this.formProfile.get('lastname')?.invalid && this.formProfile.get('lastname')?.touched;
  }

  get Country() {
    return this.formProfile.get('country')?.invalid && this.formProfile.get('country')?.touched;
  }



  submit(){
    if (this.formProfile.invalid) {
      return Object.values(this.formProfile.controls).forEach(control=>{
        control.markAllAsTouched();
      })
   }
   this.profileRequest= {lastname: this.formProfile.get('lastname')?.value, firstname: this.formProfile.get('firstname')?.value, country: this.formProfile.get('country')?.value}
   
  }
}
