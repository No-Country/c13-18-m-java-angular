import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { of, switchMap } from 'rxjs';
import { PointsDetails } from 'src/app/models/points-details';
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
  user!:any;
  isValid:boolean=false 
  details!:PointsDetails[];
  isLoading=false;
  totalPoints=0;
  constructor(
    private fb:FormBuilder,
    private loginservice:LoginService,
    private redeemCode:RedeemCodeService,
    private toastr:ToastrService,
    private router:Router
  ){}

  ngOnInit(): void {
    this.loginservice.getCurrentUser().subscribe({
      next:(user)=> this.user = user
    });
    this.formRedeemCode = this.fb.group(
      {
        code:['',[Validators.required,Validators.minLength(8),Validators.maxLength(8)],]
      }
    );
    this.formRedeemCode.get('code')?.valueChanges.subscribe(() => {
      this.onInputChange();
    });
  }
  get code(){
    return this.formRedeemCode.get('code');
  }

  validate(){
    if (this.code?.invalid || this.code?.value === ''){
      return Object.values(this.formRedeemCode.controls).forEach(control=>{
        control.markAllAsTouched();
      })
    }
    this.isLoading=true;
    this.redeemCode
    .validateCode(this.formRedeemCode.get('code')?.value)
    .pipe(
      switchMap((resp) => {
        const groupedPointsDetails: { [key: string]: { pointsEarned: number; totalGrams: number } } = {};

        resp.forEach((item) => {
          this.totalPoints += item.pointsEarned;
          const { recyclableType, pointsEarned, totalGrams } = item;
          if (!groupedPointsDetails[recyclableType]) {
            groupedPointsDetails[recyclableType] = { pointsEarned, totalGrams };
          } else {
            groupedPointsDetails[recyclableType].pointsEarned += pointsEarned;
            groupedPointsDetails[recyclableType].totalGrams += totalGrams;
          }
        });
        return of(groupedPointsDetails);
      })
    )
    .subscribe({
      
      next:(result:any) => {
      this.details = Object.keys(result).map((key) => ({
        recyclableType: key,
        pointsEarned: result[key].pointsEarned,
        totalGrams: result[key].totalGrams,
      }));
      this.isLoading=false
      this.isValid=true;
    },error:(err:HttpErrorResponse)=>{
      this.toastr.error(err.error.message,"",{
        positionClass:'toast-top-center',
        progressBar:true
      });
      this.isLoading=false;
    }
  }
    );
}
onInputChange() {
  this.isValid = false;
  this.totalPoints=0;
  this.formRedeemCode.get('code')?.markAsUntouched();
  if (this.details && this.details.length) {
    this.details.length = 0;
  }
}

  submit(){
    if (!this.isValid || this.formRedeemCode.invalid) {
      return Object.values(this.formRedeemCode.controls).forEach(control=>{
        control.markAllAsTouched();
      })
    };
    this.isLoading=true;
    const redeemCodeRequest:RedeemCode={
      userId: this.user.id,
      code: this.code?.value
    }
    this.redeemCode.redeemCode(redeemCodeRequest).subscribe({
      error:(err:HttpErrorResponse)=>{
        this.toastr.error(err.error.message,"",{
          positionClass:'toast-top-center',
          progressBar:true
        });
      },
      complete:()=>{
        this.isLoading=false;
      }
    })
  }
  show=false;
  showModal():void{
    this.show=true;
  }

  closeDialog(){
    this.router.navigate(["/home"])
  }
}
