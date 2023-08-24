import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginRequest } from 'src/app/models/login-request';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  formLogin!:FormGroup

  loginRequest!:LoginRequest

  constructor (
    private fb:FormBuilder,
    private loginservice: LoginService
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
    const loginRequest:LoginRequest={username: this.formLogin.get('email')?.value, password: this.formLogin.get('password')?.value}
    console.log(loginRequest);
    this.loginservice.login(loginRequest).subscribe({
      next:(resp)=>{
        console.log(resp);
      },
      error:()=>{
        console.log("error");
      },
      complete:()=>{
        location.reload();
      }
    });
  }

}
