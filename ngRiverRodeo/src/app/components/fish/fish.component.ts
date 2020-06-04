import { Component, OnInit } from '@angular/core';
import { FishService } from 'src/app/services/fish.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Fish } from 'src/app/models/fish';
import { River } from 'src/app/models/river';
import { RiverService } from 'src/app/services/river.service';

@Component({
  selector: 'app-fish',
  templateUrl: './fish.component.html',
  styleUrls: ['./fish.component.css']
})
export class FishComponent implements OnInit {
selected = null;
newFish = new Fish();
fishList: Fish[] = [];
currentFishId = null;
fish = new Fish();
river = new River();

  constructor(private fishSvc: FishService,  private currentRoute: ActivatedRoute,
    private router: Router, private riverSvc: RiverService, private authSvc: AuthService) { }

  ngOnInit(): void {
    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.currentFishId = this.currentRoute.snapshot.paramMap.get('id');
      this.fishSvc
        .show(this.currentRoute.snapshot.paramMap.get('id'))
        .subscribe(
          (data) => {
            this.selected = data;
          },
          (bad) => {
            this.router.navigateByUrl('nonUserLanding');
            console.error(
              'FishComponent.reload(): error retrieving fish list'
            );
            console.error(bad);
          }
        );
    }
    this.loadFishes();
  }

  loadFishes() {
    this.fishSvc.index().subscribe(
      (data) => {
        this.fishList = data;
      },
      (bad) => {
        console.error('FishComponent.loadFishes(): error loading');
        console.error(bad);
      }
    );
  }

}
