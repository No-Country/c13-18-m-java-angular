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
import { ProfileComponent } from './components/profile/profile.component';
import { DialogComponent } from './shared/components/dialog/dialog.component';
import { FaqComponent } from './components/faq/faq.component';
import { GreenPointInfoComponent } from './components/green-point-info/green-point-info.component';
import { RecyclingInfoComponent } from './components/recycling-info/recycling-info.component';
import { RecyclingInfoDetailComponent } from './components/recycling-info/recycling-info-detail/recycling-info-detail.component';

const routes: Routes = [
  
  {path: '', component: HomeComponent},
  {path: 'iniciar-sesion', component: LoginComponent, canActivate: [LoggedGuard]},
  {path: 'registro', component: RegisterComponent, canActivate: [LoggedGuard]},
  {path: 'home', component: MainComponent},
  {path: 'perfil', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'nosotros', component: AboutInfoComponent},
  {path: 'puntos-de-reciclaje', component: RecyclingPointsComponent},
  {path: 'canjear', component: RedeemComponent, canActivate: [AuthGuard]},
  {path: 'catalogo', component: CatalogueComponent},
  {path: 'premio/:id', component: RewardDetailsComponent},
  {path: 'restablecer', component: ResetpassComponent},
  {path: 'FAQ', component: FaqComponent},
  {path: 'green-point', component: GreenPointInfoComponent},
  {path: 'consejos', component: RecyclingInfoComponent},
  {path: 'consejos/:tag/:title/:id', component: RecyclingInfoDetailComponent},
  {path: 'token/confirm-reset', component: NewPassComponent},
  {path: 'token/confirm-mail/:token', component: ConfirmMailComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{anchorScrolling:'enabled',onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
