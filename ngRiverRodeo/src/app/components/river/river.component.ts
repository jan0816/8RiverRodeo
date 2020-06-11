import { RiverService } from 'src/app/services/river.service';
import { Component, OnInit } from '@angular/core';
import { River } from 'src/app/models/river';

@Component({
  selector: 'app-river',
  templateUrl: './river.component.html',
  styleUrls: ['./river.component.css']
})
export class RiverComponent implements OnInit {

  newRiver = new River();
  selectedRiver = null;
  riverList: River[] = [];
  constructor(private riverSvc: RiverService) { }

  ngOnInit(): void {
    this.loadRivers();
  }

  loadRivers() {
    this.riverSvc.index().subscribe(
      (data) => {
        this.riverList = data;
      },
      (bad) => {
        console.error('RiverComponent.loadRivers(): error loading');
        console.error(bad);
      }
    );
  }

  addRiver(river: River){
    console.log(river);
    this.riverSvc.createRiver(river).subscribe(
      good => {
        this.loadRivers();
        this.newRiver = new River()
      },
      bad => {
        console.error('River.component.addRiver(): error adding river');
        console.error(bad);
      }
    )
  }


  deleteRiver(id: number){
    this.riverSvc.destroy(id).subscribe(
      yay => {
        this.loadRivers();
      },
      boo => {
        console.error('eventListComponent.deleteTodo(): error');
        console.error(boo);

      }
    )
    this.loadRivers();
    //this.todos = this.todoService.index();
  }

  displaySingleRiver(river: River){
    console.log("Display Single River");
    console.log(river);
    this.selectedRiver = river;
  }

}
