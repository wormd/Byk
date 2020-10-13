import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ReplaySubject} from 'rxjs';
import {tap} from 'rxjs/operators';
import { Task} from '../_model/task';
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
export class TaskService {

  private url: string;
  private _short = new ReplaySubject<Task[]>();
  private _long = new ReplaySubject<Task[]>();
  private _done = new ReplaySubject<Task[]>();

  constructor(private http: HttpClient, private alertService: AlertService) {
    this.url = environment.apiUrl + 'tasks/';
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

  public fetch(dict) {
    return this.http.get<Task[]>(this.url, { params: dict2Params(dict) });
  }

  public add(emp: Task) {
    return this.http.post<Task>(this.url, emp).pipe(tap(() => {
      this.update();
      this.alertService.message("Task added.", "success");
    }, err => this.alertService.message("Task couldn't be added.", "danger"))).toPromise();
  }

  public edit(emp: Task) {
    return this.http.put<Task>(this.url, emp).pipe(tap(() => {
      this.update();
      this.alertService.message("Task edited.", "success");
    }, err => this.alertService.message("Task couldn't be edited.", "danger"))).toPromise();

  }

  public delete(id: string) {
    return this.http.delete(this.url + id, {responseType: 'text'}).pipe(tap(() => {
      this.update();
      this.alertService.message("Task deleted.", "success");
    }, err => this.alertService.message("Task couldn't be deleted.", "danger"))).toPromise();
  }

  public update() {
    // 60*60*24*7 = 1 week
    this.http.get<Task[]>(this.url, { params: dict2Params({afters: 604800}) }).subscribe(d => {
      this._short.next(d);
    });
    this.http.get<Task[]>(this.url, { params: dict2Params({befores: 604800}) }).subscribe(d => {
      this._long.next(d);
    });
    this.http.get<Task[]>(this.url, { params: dict2Params({done: true}) }).subscribe(d => {
      this._done.next(d);
    });
  }

  public done(id: string, descr: string) {
    return this.http.get<Task>(this.url+id+'/done').pipe(tap(() => {
      this.update();
      this.alertService.message(descr+' is done', 'success');
    }, err => this.alertService.message("Couldn't set `"+descr+"` to done.", "danger"))).toPromise();
  }

  public undone(id: string, descr: string) {
    return this.http.get<Task>(this.url+id+'/undone').pipe(tap(() => {
      this.update();
      this.alertService.message(descr+' has been set undone', 'success');
    }, err => this.alertService.message("Couldn't set `"+descr+"` to undone.", "danger"))).toPromise();
  }

  public clone(rem: Task) {
    const clone = Object.assign({}, rem);
    delete clone.id, clone.doneDate;
    clone.done = "false";
    console.log(clone);
    return this.http.post<Task>(this.url, clone).pipe(tap(() => {
      this.update();
      this.alertService.message(clone.descr+' has been copied and set undone', 'success');
    }, err => {
      this.alertService.message("Couldn't copy `"+clone.descr+'`.', 'danger');
    })).toPromise();
  }
}
