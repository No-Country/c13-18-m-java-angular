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
import { RecyclingPointsComponent } from './components/recycling-points/recycling-points.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoaderInterceptor } from './shared/interceptor/loader.interceptor';
import { LoaderComponent } from './shared/components/loader/loader.component';
import { PrimaryButtonComponent } from './shared/components/primary-button/primary-button.component';
import { FilledButtonComponent } from './shared/components/filled-button/filled-button.component';
import { DialogComponent } from './shared/components/dialog/dialog.component';
import { ClipboardModule } from '@angular/cdk/clipboard';
<<<<<<< HEAD
import { RecyHistorialComponent } from './components/profile/recy-historial/recy-historial.component';
import { PointsHistorialComponent } from './components/profile/points-historial/points-historial.component';
import { FaqComponent } from './components/faq/faq.component';
import { GreenPointInfoComponent } from './components/green-point-info/green-point-info.component';
=======
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
>>>>>>> 5acd519217d3f58ef43386727c56766c3d0b647d

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
    RecyclingPointsComponent,
    ProfileComponent,
    LoaderComponent,
    PrimaryButtonComponent,
    FilledButtonComponent,
    RecyHistorialComponent,
    PointsHistorialComponent,
    FaqComponent,
    GreenPointInfoComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DialogComponent,
    ClipboardModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),

  ],
  providers: [
    CookieService,
    {
      provide:HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },   
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptor,
      multi: true,
   },       
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
