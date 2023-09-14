import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ResetRequest } from 'src/app/models/reset-request';
import { ResetpassService } from 'src/app/services/resetpass.service';

@Component({
  selector: 'app-resetpass',
  templateUrl: './resetpass.component.html',
  styleUrls: ['./resetpass.component.css']
})
export class ResetpassComponent {

  formReset!:FormGroup

  resetRequest!:ResetRequest

  constructor(
    private fb:FormBuilder,
    private resetservice: ResetpassService,
    private router:Router
  ){
    this.crearForm();
  }

  crearForm(){
    this.formReset = this.fb.group(
      {
        email:['',[Validators.required,Validators.email]]
      }
    );
  }

  get Email() {
    return this.formReset.get('email')?.invalid && this.formReset.get('email')?.touched;
  }

  onSubmit(){
    if (this.formReset.invalid) {
      return Object.values(this.formReset.controls).forEach(control => {
        control.markAllAsTouched();
      });
    }
    const resetRequest:ResetRequest={email:this.formReset.get('email')?.value}
    this.resetservice.request(resetRequest).subscribe({
      next:()=>{
        alert("Mail de reestablecimiento de contraseña enviado");
      },
      error:()=>{
        alert("Mail no encontrado")
      },
      complete:()=>{
        
      }
    });
  }

}
