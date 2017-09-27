import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { RouterModule, Route } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ApartmentListingsComponent } from './apartment-listings/apartment-listings.component';

import { ApartmentDataService } from './apartment-data/apartment-data.service';
import { ApartmentDetailComponent } from './apartment-detail/apartment-detail.component';
import { LoginComponent } from './login/login.component';
import { SessionDataService } from './session-data/session-data.service';
import { MyListingsComponent } from './my-listings/my-listings.component';

const routes: Route[] = [
  { path: 'login', component: LoginComponent },
  { path: '', component: ApartmentListingsComponent },
  { path: 'apartments/mine', component: MyListingsComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    ApartmentListingsComponent,
    ApartmentDetailComponent,
    LoginComponent,
    MyListingsComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [ApartmentDataService, SessionDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
