import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RiverComponent } from './components/river/river.component';
import { TeamComponent } from './components/team/team.component';
import { FishComponent } from './components/fish/fish.component';
import { UserComponent } from './components/user/user.component';
import { TeamProfileComponent } from './components/team-profile/team-profile.component';
import { NonUserLandingComponent } from './components/non-user-landing/non-user-landing.component';
import { UserLandingComponent } from './components/user-landing/user-landing.component';
import { AboutComponent } from './components/about/about.component';
import { AdminComponent } from './components/admin/admin.component';
import { UserService } from './services/user.service';
import { AuthService } from './services/auth.service';
import { RiverService } from './services/river.service';
import { TeamService } from './services/team.service';
import { FishService } from './services/fish.service';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    NavbarComponent,
    NotFoundComponent,
    LoginComponent,
    LogoutComponent,
    RiverComponent,
    TeamComponent,
    FishComponent,
    UserComponent,
    TeamProfileComponent,
    NonUserLandingComponent,
    UserLandingComponent,
    AboutComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    UserService,
    AuthService,
    RiverService,
    TeamService,
    FishService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
