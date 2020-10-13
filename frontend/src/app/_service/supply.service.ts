import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Supply } from '../_model/supply';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class SupplyService {

  private url: string;

  constructor(private http: HttpClient, private alertService: AlertService) {
    this.url = environment.apiUrl + 'supplies/';
  }

  public add(supply: Supply) {
    return this.http.post<Supply>(this.url, supply).pipe(tap(() => {
      this.alertService.message("Supply added.", "success");
    }, err => this.alertService.message("Supply couldn't be added.", "danger"))).toPromise();
  }

  public edit(emp: Supply) {
    return this.http.put<Supply>(this.url, emp).pipe(tap(() => {
      this.alertService.message("Supply edited.", "success");
    }, err => this.alertService.message("Supply couldn't be edited.", "danger"))).toPromise();
  }

  public delete(id: string) {
    return this.http.delete(this.url + id, {responseType: 'text'}).pipe(tap(() => {
      this.alertService.message("Supply deleted.", "success");
    }, err => this.alertService.message("Supply couldn't be deleted.", "danger"))).toPromise();
  }

  public get(id: string) {
    if (id) {
      return this.http.get<Supply>(this.url + id);
    } else {
      return this.http.get<Supply>(this.url);
    }
  }
}
