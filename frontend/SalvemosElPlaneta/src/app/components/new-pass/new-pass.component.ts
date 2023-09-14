import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ResetpassService } from 'src/app/services/resetpass.service';

@Component({
  selector: 'app-new-pass',
  templateUrl: './new-pass.component.html',
  styleUrls: ['./new-pass.component.css']
})
export class NewPassComponent implements OnInit{

  token=''
  formResetPass!:FormGroup

  constructor(
    private fb:FormBuilder,
    private actRoute:ActivatedRoute,
    private resetPassService:ResetpassService,
    private router:Router
  ){}

  ngOnInit(): void {
    this.crearForm();
    this.actRoute.queryParams.subscribe(
      (params)=>{
        this.token=params["token"];
      }
    )
    
  }

  crearForm(){
    this.formResetPass = this.fb.group(
      {
        password1: ['',[Validators.required,Validators.minLength(8)]],
        password2: ['',[Validators.required,Validators.minLength(8)]]
      },{
        validators:this.passwordIguales('password1','password2')
      }
    );
  }

  get Password() {
    return this.formResetPass.get('password1')?.invalid && this.formResetPass.get('password1')?.touched;
  }

  get Password2() {
    return this.formResetPass.get('password2')?.invalid && this.formResetPass.get('password2')?.touched;
  }

  passNoValido(){
    const pass1 = this.formResetPass.get('password1')?.value;
    const pass2 = this.formResetPass.get('password2')?.value;

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
    this.passNoValido();
    if (this.formResetPass.invalid) {
      return Object.values(this.formResetPass.controls).forEach(control=>{
        control.markAllAsTouched();
      })
    }
    this.resetPassService.reset(this.token, this.formResetPass.get('password1')?.value).subscribe({
      next:()=>{
        this.router.navigate(["/login"]);
      },
      error:()=>{
        alert("No se pudo restablecer la contraseña")
      },
      complete:()=>{

      }
    })
  }

}
