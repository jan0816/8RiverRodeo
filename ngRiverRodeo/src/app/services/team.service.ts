import { Team } from './../models/team';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/teams';

  constructor(private http: HttpClient, private authService: AuthService) { }

  index() {
    const httpOptions = this.getHttpOptions();
      return this.http.get<Team[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in team service failed');
        })
        );
      }

      public show(id: number){
        const httpOptions = this.getHttpOptions();
        console.log("Show team with id "+id);
          return this.http.get<Team>(`${this.url}/${id}`, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('show single id in team service failed');
            })
          );
        }

      public update(team: Team){
        const httpOptions = this.getHttpOptions();
        if (this.authService.checkLogin()){
        return this.http.put<Team>(`${this.url}/${team.id}`, team, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('update method in team service failed');
            })
          );
        }
      }

      public disableTeam(id: number){
        const httpOptions = this.getHttpOptions();
        if (this.authService.checkLogin()){
            return this.http.delete<Team>(`${this.url}/${id}`, httpOptions)
            .pipe(
              catchError((err: any) => {
                console.log(err);
                return throwError('disable method in team service failed');
              })
            );
          }
        }

      public showLoggedInTeam(){
        const id = this.authService.getCurrentTeamId();
        const httpOptions = this.getHttpOptions();
        console.log("ShowLoggedInTeam "+id);
          return this.http.get<Team>(`${this.url}/${id}`, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('show logged in team in team service failed');
            })
          );
        }

      private getHttpOptions(){
        const credentials = this.authService.getCredentials();
        // Send credentials as Authorization header (this is spring security convention for basic auth)
        const httpOptions = {
          headers: new HttpHeaders({
            'Authorization': `Basic ${credentials}`,
            'X-Requested-With': 'XMLHttpRequest'
          })
        };
        return httpOptions;
      }
}
