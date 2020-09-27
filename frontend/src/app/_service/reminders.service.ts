import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ReplaySubject} from 'rxjs';
import {tap} from 'rxjs/operators';
import { Reminder} from '../_model/reminder';
import { environment } from '../../environments/environment';
import { AlertService } from './alert.service';

export function dict2Params(dict) {
  var params = new HttpParams();
  for(let key in dict) {
    params = params.append(key, dict[key]);
  }
  return params;
}

@Injectable({
  providedIn: 'root'
})
export class RemindersService {

  private url: string;
  private _short = new ReplaySubject<Reminder[]>();
  private _long = new ReplaySubject<Reminder[]>();
  private _done = new ReplaySubject<Reminder[]>();

  constructor(private http: HttpClient, private alertService: AlertService) {
    this.url = environment.apiUrl + 'reminders/';
  }

  get short$() {
    return this._short.asObservable();
  }

  get long$() {
    return this._long.asObservable();
  }

  get done$() {
    return this._done.asObservable();
  }

  public add(emp: Reminder) {
    return this.http.post<Reminder>(this.url, emp).pipe(tap(() => {
      this.update();
      this.alertService.message("Reminder added.", "success");
    }, err => this.alertService.message("Reminder couldn't be added.", "danger"))).toPromise();
  }

  public edit(emp: Reminder) {
    return this.http.put<Reminder>(this.url, emp).pipe(tap(() => {
      this.update();
      this.alertService.message("Reminder edited.", "success");
    }, err => this.alertService.message("Reminder couldn't be edited.", "danger"))).toPromise();

  }

  public delete(id: string) {
    return this.http.delete(this.url + id, {responseType: 'text'}).pipe(tap(() => {
      this.update();
      this.alertService.message("Reminder delete.", "success");
    }, err => this.alertService.message("Reminder couldn't be deleted.", "danger"))).toPromise();
  }

  public update() {
    // 60*60*24*7 = 1 week
    this.http.get<Reminder[]>(this.url, { params: dict2Params({afters: '604800'}) }).subscribe(d => {
      this._short.next(d);
    });
    this.http.get<Reminder[]>(this.url, { params: dict2Params({befores: '604800'}) }).subscribe(d => {
      this._long.next(d);
    });
    this.http.get<Reminder[]>(this.url, { params: dict2Params({done: true}) }).subscribe(d => {
      this._done.next(d);
    });
  }
}