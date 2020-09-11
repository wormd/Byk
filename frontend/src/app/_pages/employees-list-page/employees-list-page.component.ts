import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../../_service/employee.service';
import {Employee} from '../../_model/employee';
import {Router} from '@angular/router';
import { AuthService } from '../../_service/auth.service';
import {Transaction} from '../../_model/transaction';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EmployeeDialogAddComponent} from './employee-dialog-add/employee-dialog-add.component';
import { EmployeeDialogEditComponent } from './employee-dialog-edit/employee-dialog-edit.component';

@Component({
  selector: 'app-employees-list-page',
  templateUrl: './employees-list-page.component.html',
  styleUrls: ['./employees-list-page.component.css']
})
export class EmployeesListPageComponent implements OnInit {

  employees: Employee[];

  constructor(private router: Router, private employeeService: EmployeeService,
              private authService: AuthService, private modalService: NgbModal) { }

  ngOnInit() {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    this.employeeService.update()
    this.employeeService.employees$.subscribe(data => {
      this.employees = data;
    });
  }

  public addDialog() {
    const addComp = this.modalService.open(EmployeeDialogAddComponent);
  }

  editDialog(id) {
    const editComp = this.modalService.open(EmployeeDialogEditComponent);

    editComp.componentInstance.employee = this.employees.find(emp => +emp.id === +id);
  }

  delete(id: string) {
    this.employeeService.delete(id);
  }
}
