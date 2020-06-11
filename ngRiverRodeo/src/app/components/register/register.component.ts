import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Team } from 'src/app/models/team';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newTeam = new Team();
  newUser1 = new User();
  newUser2 = new User();

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  register(team: Team, user1: User, user2: User){
    this.newTeam.teamMembers = [];
    console.log(user1);
    console.log(user2);
    team.teamMembers.push(user1);
    team.teamMembers.push(user2);
    console.log(team);
        this.auth.register(team).subscribe(
          good => {
            this.auth.login(team.name, team.password).subscribe(
              great =>{
                this.router.navigateByUrl("/userLanding");
                console.log(great);
              },
              terrible => {
                console.error("RegisterComponent.login() Error in login after register");
                console.error(terrible);
              }
            );
          },
          bad => {
            console.error("RegisterComponent.register() method error");
            console.error(bad);
          }
        );
      }

}
