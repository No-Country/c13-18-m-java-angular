import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthInterceptor } from './auth/auth.interceptor';
import { MainComponent } from './components/main/main.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { RedeemComponent } from './components/redeem/redeem.component';
import { ConfirmMailComponent } from './components/confirm-mail/confirm-mail.component';
import { ResetpassComponent } from './components/resetpass/resetpass.component';
import { NewPassComponent } from './components/new-pass/new-pass.component';
import { AboutInfoComponent } from './components/about-info/about-info.component';
import { RewardCardComponent } from './components/catalogue/reward-card/reward-card.component';
import { RewardDetailsComponent } from './components/catalogue/reward-details/reward-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    MainComponent,
    CatalogueComponent,
    RedeemComponent,
    ConfirmMailComponent,
    ResetpassComponent,
    NewPassComponent,
    AboutInfoComponent,
    RewardCardComponent,
    RewardDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    CookieService,
    {provide:HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}          
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
