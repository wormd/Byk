import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReplaySubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Client } from '../_model/client';
import { Employee } from '../_model/employee';
import { Service } from '../_model/service';
import { Supply } from '../_model/supply';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private url: string;
  private _list = new ReplaySubject<Service[]>();
  private _services;

  constructor(private http: HttpClient, private alertService: AlertService) {
    this.url = environment.apiUrl + 'services/';
  }

  get services$() {
    if (!this._services) { this.update(); }
    return this._list.asObservable();
  }

  public add(service: Service) {
    return this.http.post<Service>(this.url, service).pipe(tap(() => {
      this.update();
      this.alertService.message("Service added.", "success");
    }, err => this.alertService.message("Service couldn't be added.", "danger"))).toPromise();
  }

  public edit(emp: Service) {
    return this.http.put<Service>(this.url, emp).pipe(tap(() => {
      this.update();
      this.alertService.message("Service edited.", "success");
    }, err => this.alertService.message("Service couldn't be edited.", "danger"))).toPromise();
  }

  public delete(id: string) {
    return this.http.delete(this.url + id, {responseType: 'text'}).pipe(tap(() => {
      this.update();
      this.alertService.message("Service deleted.", "success");
    }, err => this.alertService.message("Service couldn't be deleted.", "danger"))).toPromise();
  }

  public get(id: string) {
    return this.http.get<Service>(this.url + id);
  }

  public toggleActive(id: string) {
    return this.http.get(this.url + id + "/toggle").pipe(tap(() => {
      // pass
    }, err => this.alertService.message("Service couldn't be set active.", "danger"))).toPromise();
  }

  public update() {
    this.http.get<Service[]>(this.url).subscribe(d => {
      this._services = d;
      this._list.next(d);
    });
  }

  public addClient(id: string, clientId: string) {
    return this.http.get(this.url + id + "/clients/"+clientId+"/add");
  }

  public removeClient(id: string, clientId: string) {
    return this.http.get<Client[]>(this.url + id + "/clients/"+clientId+"/remove");
  }

  public addSupply(id: string, supplyId: string) {
    console.log(this.url + id + "/supplies/"+supplyId+"/add");
    return this.http.get(this.url + id + "/supplies/"+supplyId+"/add");
  }

  public removeSupply(id: string, supplyId: string) {
    return this.http.get<Supply[]>(this.url + id + "/supplies/"+supplyId+"/remove");
  }

  public addEmployee(id: string, supplyId: string) {
    console.log(this.url + id + "/employees/"+supplyId+"/add");
    return this.http.get(this.url + id + "/employees/"+supplyId+"/add");
  }

  public removeEmployee(id: string, supplyId: string) {
    return this.http.get<Employee[]>(this.url + id + "/employees/"+supplyId+"/remove");
  }
}
