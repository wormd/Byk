import {Component, OnInit} from '@angular/core';
import {Employee} from '../../../_model/employee';
import {ActivatedRoute, Router} from '@angular/router';
import {EmployeeService} from '../../../_service/employee.service';
import {NgbActiveModal, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-employee-dialog-add',
  templateUrl: './employee-dialog-add.component.html',
  styleUrls: ['./employee-dialog-add.component.css']
})

export class EmployeeDialogAddComponent implements OnInit {

  employee: Employee;
  dateModel: NgbDateStruct;
  
  constructor(private route: ActivatedRoute, private router: Router,
              private employeeService: EmployeeService, public modal: NgbActiveModal) {
      this.employee = new Employee();
    }

  onSubmit() {
    this.dateModel ? this.employee.since = new Date(Date.UTC(this.dateModel.year, this.dateModel.month - 1, this.dateModel.day)) : this.employee.since = new Date();
    this.employeeService.add(this.employee).then(data => {
      this.modal.close('Ok click');
    });
  }

  ngOnInit() {
  }

}
