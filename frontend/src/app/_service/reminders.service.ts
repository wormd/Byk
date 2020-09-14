import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ReplaySubject} from 'rxjs';
import {tap} from 'rxjs/operators';
import { Reminder} from '../_model/reminder';
import { environment } from '../../environments/environment';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class RemindersService {

  private url: string;
  private _list = new ReplaySubject<Reminder[]>();
  private _reminders: Reminder[];

  constructor(private http: HttpClient, private alertService: AlertService) {
    this.url = environment.apiUrl + 'reminders/';
  }

  get reminders$() {
    if (!this._reminders) { this.update(); }
    return this._list.asObservable();
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
    this.http.get<Reminder[]>(this.url).subscribe(d => {
      this._reminders = d;
      this._list.next(d);
    });
  }
}
