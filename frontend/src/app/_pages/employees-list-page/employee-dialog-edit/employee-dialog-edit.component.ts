import {Component, OnInit, Input} from '@angular/core';
import {Employee} from '../../../_model/employee';
import {ActivatedRoute, Router} from '@angular/router';
import {EmployeeService} from '../../../_service/employee.service';
import {NgbActiveModal, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-employee-dialog-edit',
  templateUrl: './employee-dialog-edit.component.html',
  styleUrls: ['./employee-dialog-edit.component.css']
})
export class EmployeeDialogEditComponent implements OnInit {

  @Input() employee: Employee;
  dateSinceModel: NgbDateStruct;
  dateUntilModel: NgbDateStruct;
  
  constructor(private route: ActivatedRoute, private router: Router,
              private employeeService: EmployeeService, public modal: NgbActiveModal) {
  }

  ngOnInit() {
    console.log(this.employee.since.getMonth());
    this.dateSinceModel.year = this.employee.since.getFullYear();
    this.dateSinceModel.month = this.employee.since.getMonth() + 1;
    this.dateSinceModel.day = this.employee.since.getDay();
    if (this.employee.until) {
      this.dateUntilModel.year = this.employee.until.getFullYear();
      this.dateUntilModel.month = this.employee.until.getMonth() + 1;
      this.dateUntilModel.day = this.employee.until.getDay();
    }
  }

  onSubmit() {
    this.dateSinceModel ? this.employee.since = new Date(Date.UTC(this.dateSinceModel.year, this.dateSinceModel.month - 1, this.dateSinceModel.day)) :
     this.employee.since = new Date();
    if (this.dateUntilModel) {
      this.employee.until = new Date(Date.UTC(this.dateSinceModel.year, this.dateSinceModel.month - 1, this.dateSinceModel.day));
    }
    this.employeeService.edit(this.employee).then(data => {
      this.modal.close('Ok click');
    });
  }

}
