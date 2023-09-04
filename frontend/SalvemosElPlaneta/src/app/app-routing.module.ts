import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { MainComponent } from './components/main/main.component';
import { RedeemComponent } from './components/redeem/redeem.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { ConfirmMailComponent } from './components/confirm-mail/confirm-mail.component';
import { ResetpassComponent } from './components/resetpass/resetpass.component';
import { NewPassComponent } from './components/new-pass/new-pass.component';
import { AboutInfoComponent } from './components/about-info/about-info.component';
import { RewardDetailsComponent } from './components/catalogue/reward-details/reward-details.component';
import { RecyclingPointsComponent } from './components/recycling-points/recycling-points.component';
import { AuthGuard } from './guards/auth.guard';
import { LoggedGuard } from './guards/logged.guard';

const routes: Routes = [
  
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent, canActivate: [LoggedGuard]},
  {path: 'registro', component: RegisterComponent, canActivate: [LoggedGuard]},
  {path: 'home', component: MainComponent},
  {path: 'about-info', component: AboutInfoComponent},
  {path: 'recy-points', component: RecyclingPointsComponent},
  {path: 'canjear', component: RedeemComponent, canActivate: [AuthGuard] },
  {path: 'catalogo', component: CatalogueComponent},
  {path: 'premio/:id', component: RewardDetailsComponent},
  {path: 'restablecer', component: ResetpassComponent},
  {path: 'token/confirm-reset', component: NewPassComponent},
  {path: 'token/confirm-mail/:token', component: ConfirmMailComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
