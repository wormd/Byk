import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Service } from 'src/app/_model/service';
import { AuthService } from 'src/app/_service/auth.service';
import { ClientService } from 'src/app/_service/client.service';
import { EmployeeService } from 'src/app/_service/employee.service';
import { ServiceService } from 'src/app/_service/service.service';
import { SupplyService } from 'src/app/_service/supply.service';
import { AddToListComponent } from './add-to-list/add-to-list.component';
import { SupplyDialogAddComponent } from './supply-dialog-add/supply-dialog-add.component';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.css']
})
export class ServicePageComponent implements OnInit {

  service: Service;
  staffLock = true;
  suppliesLock = true;
  clientsLock = true;
  id: string;

  constructor(private router: Router, private route: ActivatedRoute, 
    private authService: AuthService, public serviceService: ServiceService,
    private modalService: NgbModal, private employeeService: EmployeeService,
    private supplyService: SupplyService, private clientService: ClientService) {
     }

  ngOnInit(): void {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    this.id = this.route.snapshot.paramMap.get('id');
    this.update(this.id);
  }

  update(id: string) {
    this.serviceService.get(this.id).subscribe(d => this.service = d);
  }

  add2Staff() {
    this.employeeService.employees$.subscribe(emps => {
      const modalRef = this.modalService.open(AddToListComponent);
      modalRef.componentInstance.list = emps;
      modalRef.componentInstance.selected.subscribe(res => {
        this.serviceService.addEmployee(this.id, res.id).subscribe(d=>this.service.staff.push(res));
      });
    });
  }

  add2Supplies() {
    this.supplyService.get(null).subscribe(d => {
      const modalRef = this.modalService.open(SupplyDialogAddComponent);
      modalRef.componentInstance.list = d;
      modalRef.componentInstance.selected.subscribe(res => {
        this.serviceService.addSupply(this.id, res.id).subscribe(d=>this.service.supplies.push(res));
      });
    });
  }

  add2Clients() {
    this.clientService.get(null).subscribe(clients => {
      const modalRef = this.modalService.open(AddToListComponent);
      modalRef.componentInstance.list = clients;
      modalRef.componentInstance.selected.subscribe(res => {
        this.serviceService.addClient(this.id, res.id).subscribe(d=>this.service.clients.push(res));
      });
    });
  }

  clientRemove(event) {
    this.serviceService.removeClient(this.id, event).subscribe(d=>this.service.clients = d)
  }

  employeeRemove(event) {
    this.serviceService.removeEmployee(this.id, event).subscribe(d=>this.service.staff = d)
  }

  supplyRemove(event) {
    this.serviceService.removeSupply(this.id, event).subscribe(d=>this.service.supplies = d)
  }
}
