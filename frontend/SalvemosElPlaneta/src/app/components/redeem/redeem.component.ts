import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RedeemCode } from 'src/app/models/redeem-code';
import { LoginService } from 'src/app/services/login.service';
import { RedeemCodeService } from 'src/app/services/redeem-code.service';

@Component({
  selector: 'app-redeem',
  templateUrl: './redeem.component.html',
  styleUrls: ['./redeem.component.css']
})
export class RedeemComponent implements OnInit{

  formRedeemCode!:FormGroup
  redeemCodeRequest!:RedeemCode
  user!:any;
  isValid:boolean=false

  constructor(
    private fb:FormBuilder,
    private loginservice:LoginService,
    private redeemCode:RedeemCodeService,
  ){}

  ngOnInit(): void {
    this.loginservice.getCurrentUser().subscribe({
      next:(user)=> this.user = user
    });
    this.crearForm();
  }

  crearForm (){
    this.formRedeemCode = this.fb.group(
      {
        code:['',[Validators.required,Validators.minLength(8),Validators.maxLength(8)],]
      }
    );
  }

  get Code() {
    return this.formRedeemCode.get('code')?.invalid && this.formRedeemCode.get('code')?.touched;
  }

  validate(){
    this.redeemCode.validateCode(this.formRedeemCode.get('code')?.value).subscribe({
      next:()=>{
        console.log("validado")
      },
      error:()=>{
        console.error();
      },
      complete:()=>{

      }
    });
  }

  submit(){
    if (this.formRedeemCode.invalid) {
      return Object.values(this.formRedeemCode.controls).forEach(control=>{
        control.markAllAsTouched();
      })
    };
    this.redeemCodeRequest= {userId: this.user.id ,code: this.formRedeemCode.get('code')?.value};
    console.log(this.redeemCodeRequest);
    this.redeemCode.redeemCode(this.redeemCodeRequest).subscribe({
      next:()=>{
        console.log("código canjeado correctamente.")
      },
      error:()=>{
        console.log("Error al canjear el código.")
      },
      complete:()=>{

      }
    })
  }
}
