import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-resetpass',
  templateUrl: './resetpass.component.html',
  styleUrls: ['./resetpass.component.css']
})
export class ResetpassComponent {

  formReset!:FormGroup

  resetRequest!:''

  constructor(
    private fb:FormBuilder,
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
  }

}
