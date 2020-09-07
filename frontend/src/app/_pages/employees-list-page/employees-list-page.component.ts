import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../../_service/employee.service';
import {Employee} from '../../_model/employee';
import {Router} from '@angular/router';
import { AuthService } from '../../_service/auth.service';

@Component({
  selector: 'app-employees-list-page',
  templateUrl: './employees-list-page.component.html',
  styleUrls: ['./employees-list-page.component.css']
})
export class EmployeesListPageComponent implements OnInit {

  employees: Employee[];

  constructor(private router: Router, private employeeService: EmployeeService, private authService: AuthService) { }

  ngOnInit() {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    this.employeeService.getAll().subscribe(data => {
      this.employees = data;
    });
  }

  delete(id: string) {
    this.employeeService.delete(id).subscribe(data => this.ngOnInit())
  }

}
