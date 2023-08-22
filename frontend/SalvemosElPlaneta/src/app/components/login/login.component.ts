import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  formLogin!:FormGroup

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
        password: ['',[Validators.required,Validators.minLength(6)]]
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
    console.log(this.formLogin);
    if (this.formLogin.invalid) {
      return Object.values(this.formLogin.controls).forEach(control => {
        control.markAllAsTouched();
      });
    }
      //    else {
    //   this.loginservice.login(this.formLogin.value);
    // }
  }

}
