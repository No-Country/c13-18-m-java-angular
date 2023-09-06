import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { LoginRequest } from 'src/app/models/login-request';
import { LoaderService } from 'src/app/services/loader.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  formLogin!:FormGroup
  isLoading=false;
  loginRequest!:LoginRequest
  constructor (
    private fb:FormBuilder,
    private loginservice: LoginService,
    private routes: Router,
    private loaderService:LoaderService
  ) {
    this.crearForm();
  }

  crearForm() {
    this.formLogin = this.fb.group(
      {
        email:['',[Validators.required,Validators.email]],
        password: ['',[Validators.required,Validators.minLength(8)]]
      }
    );
  }

  get Email() {
    return this.formLogin.get('email')?.invalid && this.formLogin.get('email')?.touched;
  }

  get Password() {
    return this.formLogin.get('password')?.invalid && this.formLogin.get('password')?.touched;
  }

  onSubmit(){
    if (this.formLogin.invalid) {
      return Object.values(this.formLogin.controls).forEach(control => {
        control.markAllAsTouched();
      });
    }
    this.isLoading=true;
    const loginRequest:LoginRequest={username: this.formLogin.get('email')?.value, password: this.formLogin.get('password')?.value}
    this.loginservice.login(loginRequest).subscribe({
      next:(resp)=>{
        this.isLoading=!this.isLoading
        this.routes.navigate(['/home']);
      },
      error:()=>{
        this.isLoading=!this.isLoading
      },
      complete:()=>{
        this.isLoading=!this.isLoading
      }
    });
  }

}
