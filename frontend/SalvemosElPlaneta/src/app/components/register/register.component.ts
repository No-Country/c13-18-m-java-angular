import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  formRegister!:FormGroup

  constructor (
    private formbuilder: FormBuilder,
    private router: Router
  ){
    this.crearForm();
  }

  crearForm (){
    this.formRegister = this.formbuilder.group(
      {
        email:['',[Validators.required,Validators.email]],
        username:['',[Validators.required,Validators.minLength(5)]],
        password1: ['',[Validators.required,Validators.minLength(6)]],
        password2: ['',[Validators.required,Validators.minLength(6)]]
      },{
        validators:this.passwordIguales('password1','password2')
      }
    );
  }

  get Email() {
    return this.formRegister.get('email')?.invalid && this.formRegister.get('email')?.touched;
  }

  get Username() {
    return this.formRegister.get('username')?.invalid && this.formRegister.get('username')?.touched;
  }

  get Password() {
    return this.formRegister.get('password1')?.invalid && this.formRegister.get('password1')?.touched;
  }

  get Password2() {
    return this.formRegister.get('password2')?.invalid && this.formRegister.get('password2')?.touched;
  }

  get password2NoValido(){
    return this.formRegister.get('password2')?.invalid && this.formRegister.get('password2')?.touched;
  }

  passNoValido(){
    const pass1 = this.formRegister.get('password1')?.value;
    const pass2 = this.formRegister.get('password2')?.value;

    if (pass1 !== pass2) {
      return true;
    } else {
      return false;
    }

  }

  passwordIguales (pass1Name:string, pass2Name:string){

    return (formGroup:FormGroup) => {
      const pass1Control = formGroup.get(pass1Name);
      const pass2Control = formGroup.get(pass2Name);

      if (pass1Control?.value === pass2Control?.value) {
        pass2Control?.setErrors(null);
      } else {
        pass2Control?.setErrors({noEsIgual:true});
      }
    }

  }

  submit(){
    console.log(this.formRegister);
    this.passNoValido();
    if (this.formRegister.invalid) {
      return Object.values(this.formRegister.controls).forEach(control=>{
        control.markAllAsTouched();
      })
    }
  }

}
