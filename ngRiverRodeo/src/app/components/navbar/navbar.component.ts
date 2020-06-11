import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { Team } from 'src/app/models/team';
import { TeamService } from 'src/app/services/team.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  closeResult = '';
  editTeam = null;
  currTeam = null;
  newUser1 = new User ();
  newUser2 = new User();
  newTeam = new Team();
  pictureUrl= "https://www.w3schools.com/bootstrap4/bird.jpg";
  selected = null;
  isCollapsed = true;

  constructor( private authService: AuthService,
    private teamService: TeamService,
    private userService: UserService,
    private modalService: NgbModal,
    private router: Router) { }

  ngOnInit(): void {
    console.log("NavBar ngOnInit");
    if (this.loggedIn()){
      console.log("logged in");
      this.getLoggedInTeam();
    }
  }
  loggedIn(){
    return this.authService.checkLogin();
  }

  getLoggedInTeam(){
    this.teamService.showLoggedInTeam().subscribe(
      data => {
        console.log("GetLoggedInTeam")
        this.currTeam = data;
        this.pictureUrl = data.pictureUrl;
      },
      err => {
        console.log("Error in getLoggedInUser "+err);
      }
    )
  }

  adminLoggedIn(){
    if (this.loggedIn()){
     return this.authService.getCurrentUserRole() === 'admin';
    }
    return false;
  }

  login(team: Team){
    this.authService.login(team.name, team.password).subscribe(
      good => {
        console.log(good);
        this.currTeam = good;
        this.pictureUrl = good.pictureUrl;
        this.router.navigateByUrl("/profile");
        this.newTeam = new Team();
      },
      bad => {
        console.log("Error logging in User:" + bad);
      });
  }

  register(user: User, location: Location){
    console.log(user);
    console.log(location);
    this.locaSvc.createLocation(location).subscribe(
      location => {
        console.log("Create location");
        console.log(location);
        this.returnedLocation = location;
        user.location = this.returnedLocation;
        this.authService.register(user).subscribe(
          good => {
            this.authService.login(user.username,user.password).subscribe(
              great =>{
                this.router.navigateByUrl("/profile");
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

      },
      err =>{
        console.log("Error creating location in user register "+err);
      }
    );
  }

  setEditUser(){
    this.editUser = Object.assign({}, this.currUser);
  }

  updateUser(user: User){
    this.userService.update(user).subscribe(
      yes => {
        this.reload();
        //this.currentUser = yes;
        this.editUser = null;
      },
      no => {
        console.error('UserProfileComponent.updateUser(): error');
        console.error(no);

      }
    );
    //this.todos = this.todoService.index();
  }

  reload(){
    this.userService.showLoggedInUser().subscribe(
      data => {
        this.currUser = data;
        this.userImg = data.imageUrl;
        console.log(data);
      },
      error =>{
        console.log("error inside show logged in user");
      }
    );
  }

}
