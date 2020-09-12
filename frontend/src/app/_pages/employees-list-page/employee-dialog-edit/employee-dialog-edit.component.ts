import {Component, OnInit, Input} from '@angular/core';
import {Employee} from '../../../_model/employee';
import {ActivatedRoute, Router} from '@angular/router';
import {EmployeeService} from '../../../_service/employee.service';
import {NgbActiveModal, NgbDate, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

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
  }

  onSubmit() {
    const newEmployee = Object.assign({}, this.employee);
    if (this.dateSinceModel) {
      newEmployee.since = new Date(Date.UTC(this.dateSinceModel.year, this.dateSinceModel.month - 1, this.dateSinceModel.day))
    }
    if (this.dateUntilModel) {
      this.employee.until = new Date(Date.UTC(this.dateSinceModel.year, this.dateSinceModel.month - 1, this.dateSinceModel.day));
    }
    this.employeeService.edit(this.employee).then(data => {
      this.modal.close('Ok click');
    });
  }
}
