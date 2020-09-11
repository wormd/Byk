import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, ReplaySubject} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Employee} from '../_model/employee';
import { environment } from '../../environments/environment';
import { AlertService } from './alert.service';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private url: string;
  private _list = new ReplaySubject<Employee[]>();
  private _employees;

  constructor(private http: HttpClient, private alertService: AlertService) {
    this.url = environment.apiUrl + 'employees/';
  }

  get employees$() {
    if (!this._employees) { this.update(); }
    return this._list.asObservable();
  }

  public add(emp: Employee) {
    return this.http.post<Employee>(this.url, emp).pipe(tap(() => {
      this.update();
      this.alertService.message("Employee added.", "success");
    }, err => this.alertService.message("Employee couldn't be added.", "danger"))).toPromise();
  }

  public edit(emp: Employee) {
    return this.http.put<Employee>(this.url, emp).pipe(tap(() => {
      this.update();
      this.alertService.message("Employee edited.", "success");
    }, err => this.alertService.message("Employee couldn't be edited.", "danger"))).toPromise();

  }

  public delete(id: string) {
    return this.http.delete(this.url + id, {responseType: 'text'}).pipe(tap(() => {
      this.update();
      this.alertService.message("Employee delete.", "success");
    }, err => this.alertService.message("Employee couldn't be deleted.", "danger"))).toPromise();
  }

  public update() {
    this.http.get<Employee[]>(this.url).subscribe(d => {
      this._employees = d;
      this._list.next(d);
    });
  }
}
