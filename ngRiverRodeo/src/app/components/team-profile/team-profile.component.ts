import { Component, OnInit } from '@angular/core';
import { TeamService } from 'src/app/services/team.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Team } from 'src/app/models/team';

@Component({
  selector: 'app-team-profile',
  templateUrl: './team-profile.component.html',
  styleUrls: ['./team-profile.component.css']
})
export class TeamProfileComponent implements OnInit {
  closeResult = '';

  currentTeam = null;
  editTeam = null;
  selected= null;



  constructor(private teamService:TeamService, private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService, private modalService: NgbModal) { }


  ngOnInit(): void {
    this.reload();
  }

  setEditTeam(){
    this.editTeam = Object.assign({}, this.currentTeam());
  }

  updateTeam(team: Team){
    this.teamService.update(team).subscribe(
      yes => {
        this.reload();
        //this.currentUser = yes;
        this.editTeam = null;
      },
      no => {
        console.error('TeamProfileComponent.updateTeam(): error');
        console.error(no);

      }
    );
    //this.todos = this.todoService.index();
  }

  openMed(content) {
    this.modalService.open(content, { size: 'md' });
  }


  reload(){
    this.teamService.showLoggedInTeam().subscribe(
      data => {
        this.currentTeam = data;
        console.log(data);
      },
      error =>{
        console.log("error inside show logged in team");
      }
    );
  }
}

