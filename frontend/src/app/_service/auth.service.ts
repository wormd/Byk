import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Subject} from 'rxjs';
import {User} from '../_model/user';
import {tap} from 'rxjs/operators';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url: string;
  private _currentUser = new BehaviorSubject<User>(undefined);

  constructor(private http: HttpClient) {
    this.url = environment.apiUrl;
  }

  fetchCurrentUser() {
    this.http.get<User>(this.url + 'currentUser').subscribe(
      i => this._currentUser.next(i),
      error => this.logout()
    );
  }

  get currentUser$() {
    return this._currentUser.asObservable();
  }

  login(user, pwd) {
    const ret = new Subject();
    return this.http.post<any>(this.url + 'login',
      {username: user, password: pwd})
      .pipe(tap(i => {
      localStorage.setItem('token', i.token);
      ret.next(i);
      this.fetchCurrentUser();
    }));
  }

  signup(user, pwd) {
    const ret = new Subject();
    return this.http.post<any>(this.url + 'signup',
      {username: user, password: pwd})
      .pipe(tap(i => {
      ret.next(i);
    }));
  }

  loggedIn() {
    const token = localStorage.getItem('token');
    return !!token;
  }

  logout() {
    localStorage.removeItem('token');
    this._currentUser.next(undefined);
  }
}
