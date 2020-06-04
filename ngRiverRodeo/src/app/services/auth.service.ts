import { Team } from './../models/team';
import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  login(username, password) {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    console.log(username+" "+password);
    console.log(credentials);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
        // create request to authenticate credentials
        return this.http
        .get<Team>(this.baseUrl + 'authenticate', httpOptions)
        .pipe(
          tap((team) => {
            localStorage.setItem('credentials' , credentials);
            localStorage.setItem('currentTeamId' , team.id+"");
            localStorage.setItem('currentTeamRole' , team.role+"");
            //console.log(res);
            return team;
          }),
          catchError((err: any) => {
            console.log(err);
            return throwError('AuthService.login(): Error logging in.');
          })
        );
        }


    register(team: Team) {
      // create request to register a new account
      return this.http.post(this.baseUrl + 'register', team)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.register(): error registering team.');
        })
      );
    }

    getCurrentUserId(){
      return localStorage.getItem('currentTeamId');
    }

    getCurrentUserRole(){
      return localStorage.getItem('currentTeamRole');
    }

    logout() {
      localStorage.removeItem('credentials');
      localStorage.removeItem('currentUserId');
      localStorage.removeItem('currentUserRole');
    }

    checkLogin() {
      if (localStorage.getItem('credentials')) {
        return true;
      }
      return false;
    }

    generateBasicAuthCredentials(username, password) {
      return btoa(`${username}:${password}`);
    }

    getCredentials() {
      return localStorage.getItem('credentials');
    }
}
