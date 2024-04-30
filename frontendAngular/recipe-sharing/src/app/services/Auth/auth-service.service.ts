import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { tap } from 'rxjs/internal/operators/tap';
import { ApiRoute } from '../../ApiRoute/api-route';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {


  constructor(private http:HttpClient) { }

  authSubject = new BehaviorSubject<any>({
    user:null
  })

  login(userData:any):Observable<any>{
    return this.http.post<any>(ApiRoute.SIGN_IN,userData)
  }

  register(userData:any):Observable<any>{
    return this.http.post<any>(ApiRoute.SIGN_UP,userData)
  }

  getUserProfile():Observable<any>{
    const headers = new HttpHeaders({
      Authorization:`Bearer ${localStorage.getItem("jwt")}`
    })
    
    return this.http.get<any>(ApiRoute.GET_USER_PROFILE,{headers}).pipe(
      tap((user)=>{
        console.log("get user profile",user)
        const currentState=this.authSubject.value;
        this.authSubject.next({...currentState,user})
      })
    )
  }

  logout(){
    localStorage.clear()
    this.authSubject.next({})
  }
}
