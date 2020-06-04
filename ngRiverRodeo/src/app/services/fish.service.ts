import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { River } from '../models/river';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Fish } from '../models/fish';

@Injectable({
  providedIn: 'root'
})
export class FishService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api';
  river = new River();

  constructor(private http: HttpClient, private auth: AuthService) { }

  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${this.auth.getCredentials()}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }

  public index() {
    return this.http.get<Fish[]>(this.url + '/events').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('FishService.index: error retrieving fishes: ' + err);
      })
    );
  }

  public show(id) {
    return this.http.get<Fish>(`${this.url}/fishes/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('FishService.show: error retrieving event: ' + err);
      })
    );
  }
  public create(fish: Fish, river: River){
    const httpOptions = this.getHttpOptions();
    console.log(event);
    if (this.auth.checkLogin()){
      return this.http.post<Fish>(`${this.url}/fishes/${fish.id}`, fish, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('create method in Fish service failed');
          })
        );
    }
  }

  // public update(event: Event){
  //   const httpOptions = this.getHttpOptions();
  //   if (this.auth.checkLogin()){
  //   return this.http.put<Event>(`${this.url}/activities/${event.activity.id}/events/${event.id}`, event, httpOptions)
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError('update method in Event service failed');
  //       })
  //     );
  //   }
  // }

  // public destroy(id: number){
  //   const httpOptions = this.getHttpOptions();
  //   console.log(typeof id);
  //   console.log(id);

  //   if (this.auth.checkLogin()){
  //       return this.http.delete<Event>(`${this.url}/events/${id}`, httpOptions)
  //       .pipe(
  //         catchError((err: any) => {
  //           console.log(err);
  //           return throwError('delete method in Event service failed');
  //         })
  //       );
  //     }
  //   }

}
