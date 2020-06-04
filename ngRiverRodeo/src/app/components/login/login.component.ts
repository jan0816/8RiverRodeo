import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Team } from 'src/app/models/team';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newTeam = new Team();

  constructor(private authService:AuthService, private router: Router,) { }

  ngOnInit(): void {
  }

  login(team:Team){
    this.newTeam = team;
    console.log(this.newTeam);
    this.authService.login(team.name, team.password).subscribe(
      good => {
        console.log(good);
        this.router.navigateByUrl("/userLanding");
      })
      this.newTeam = new Team();
      bad => {
        console.log("Error in login component: " + bad);
      }
  }

}
