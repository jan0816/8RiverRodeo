import { River } from './../models/river';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RiverService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/rivers';

  constructor(private http: HttpClient, private authService: AuthService) { }

  index() {
    const httpOptions = this.getHttpOptions();
      return this.http.get<River[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in river service failed');
        })
        );
      }

      public show(id: number){
        const httpOptions = this.getHttpOptions();
        console.log("Show river with id "+id);
          return this.http.get<River>(`${this.url}/${id}`, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('show single id in river service failed');
            })
          );
        }


      public update(river: River){
        const httpOptions = this.getHttpOptions();
        if (this.authService.checkLogin()){
        return this.http.put<River>(`${this.url}/${river.id}`, river, httpOptions)
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError('update method in river service failed');
            })
          );
        }
      }

      public destroy(id: number){
        return this.http.delete<River>(`${this.url}/${id}`)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('delete method in river service failed');
          })
        );
      }


    public createRiver(river: River) {
      // create request to register a new account
      return this.http.post(this.baseUrl + 'rivers', river)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(': error creating river.');
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
