import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileRequest } from 'src/app/models/profile-request';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  isLogged!:boolean;
  isEditMode=false;
  user!:User;
  userId!:number;
  formProfile!:FormGroup
  profileRequest!:ProfileRequest
  tabActive = 'reciclajes'

  constructor(
    private authService:LoginService,
    private fb:FormBuilder,
    private profService:ProfileService
    ){}


  ngOnInit():void{
    this.authService.getCurrentUser().subscribe({
      next:(user)=> {
        if (user) {
          this.user = user
          this.crearForm(this.user)
          this.userId = user.id
        };
      }
    });
    this.isLogged = this.authService.isLogged();
  }

  getUser(){
    this.authService.getCurrentUser().subscribe({
      next:(user)=> {
        if (user) {
          this.user = user
          this.userId = user.id
        };
      }
    });
  }
  updateUser(){
    this.authService.getCurrentUser().subscribe({
      next:(user)=> {
        if (user) {
          this.user = user
        };
      }
    });
    this.formProfile.patchValue({
      firstname:this.user.firstname,
      lastname:this.user.lastname,
      country:this.user.country
    })

  }
  
  

  crearForm(user:User){
    this.formProfile = this.fb.group(
      {
        firstname:[{value: user.firstname??'',disabled:true},[Validators.required,Validators.minLength(2)]],
        lastname:[{value: user.lastname??'', disabled:true},[Validators.required,Validators.minLength(2)]],
        country: [{value: user.country??'', disabled:true},[Validators.required,Validators.minLength(2)]],
      }
    );
  }

  editMode(){
    for(let control in this.formProfile.controls){
      if(this.formProfile.controls[control].disabled){
        this.formProfile.controls[control].enable()
        this.isEditMode=true;
      }else{
        this.formProfile.controls[control].disable()
        this.isEditMode=false;
      }
    }   
  }

  get Firstname() {
    return this.formProfile.get('firstname')?.invalid && this.formProfile.get('firstname')?.touched;
  }

  get Lastname() {
    return this.formProfile.get('lastname')?.invalid && this.formProfile.get('lastname')?.touched;
  }

  get Country() {
    return this.formProfile.get('country')?.invalid && this.formProfile.get('country')?.touched;
  }

  setTab(tab:string){
    this.tabActive = tab;
  }

  submit(){
    if (this.formProfile.invalid) {
      return Object.values(this.formProfile.controls).forEach(control=>{
        control.markAllAsTouched();
      })
   }
   this.profileRequest= {lastname: this.formProfile.get('lastname')?.value, firstname: this.formProfile.get('firstname')?.value, country: this.formProfile.get('country')?.value}
   this.profService.userModif(this.userId , this.profileRequest).subscribe({
    next:()=>{
    },
    error:()=>{

    },
    complete:()=>{
      this.updateUser()
      // this.isEditMode = false

    }
   })
  }

  ngOnDestroy(): void {
    
  }
}
