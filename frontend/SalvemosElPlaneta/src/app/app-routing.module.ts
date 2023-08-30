import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { MainComponent } from './components/main/main.component';
import { RedeemComponent } from './components/redeem/redeem.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { ConfirmMailComponent } from './components/confirm-mail/confirm-mail.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch:'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registro', component: RegisterComponent},
  {path: 'main', component: MainComponent},
  {path: 'canjear', component: RedeemComponent},
  {path: 'catalogo', component: CatalogueComponent},
  {path: ':token', component: ConfirmMailComponent} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
