import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { TeamProfileComponent } from './components/team-profile/team-profile.component';
import { AboutComponent } from './components/about/about.component';
import { NonUserLandingComponent } from './components/non-user-landing/non-user-landing.component';
import { UserLandingComponent } from './components/user-landing/user-landing.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminComponent } from './components/admin/admin.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RiverComponent } from './components/river/river.component';
import { FishComponent } from './components/fish/fish.component';
import { TeamComponent } from './components/team/team.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'nonUserLanding' },
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent},
  { path: 'profile', component: TeamProfileComponent},
  { path: 'about', component: AboutComponent},
  { path: 'rivers/:id', component: RiverComponent},
  { path: 'rivers', component: RiverComponent},
  { path: 'fishes', component: FishComponent},
  { path: 'fishes/:id', component: FishComponent},
  { path: 'teams', component: TeamComponent},
  { path: 'teams/:id', component: TeamComponent},
  { path: 'nonUserLanding', component: NonUserLandingComponent},
  { path: 'userLanding', component: UserLandingComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'admin', component: AdminComponent},
  { path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true}) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
