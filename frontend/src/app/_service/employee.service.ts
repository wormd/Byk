import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Employee} from '../_model/employee';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.apiUrl+'employees';
  }

  public getAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.url);
  }

  public save(employee: Employee) {
    return this.http.post<Employee>(this.url, employee);
  }

  public delete(id: string) {
    return this.http.delete(this.url + '/' + id, {responseType: 'text'});
  }
}
