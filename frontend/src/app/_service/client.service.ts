import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Client } from '../_model/client';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private url: string;

  constructor(private http: HttpClient, private alertService: AlertService) {
    this.url = environment.apiUrl + 'clients/';
  }

  public add(service: Client) {
    return this.http.post<Client>(this.url, service).pipe(tap(() => {
      this.alertService.message("Service added.", "success");
    }, err => this.alertService.message("Service couldn't be added.", "danger"))).toPromise();
  }

  public edit(emp: Client) {
    return this.http.put<Client>(this.url, emp).pipe(tap(() => {
      this.alertService.message("Service edited.", "success");
    }, err => this.alertService.message("Service couldn't be edited.", "danger"))).toPromise();
  }

  public delete(id: string) {
    return this.http.delete(this.url + id, {responseType: 'text'}).pipe(tap(() => {
      this.alertService.message("Service deleted.", "success");
    }, err => this.alertService.message("Service couldn't be deleted.", "danger"))).toPromise();
  }

  public get(id: string) {
    if (id) {
      return this.http.get<Client>(this.url + id);
    } else {
      return this.http.get<Client>(this.url);
    }
  }

}
