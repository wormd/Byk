import {Component, OnInit} from '@angular/core';
import {Employee} from '../../../_model/employee';
import {ActivatedRoute, Router} from '@angular/router';
import {EmployeeService} from '../../../_service/employee.service';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-employee-dialog-add',
  templateUrl: './employee-dialog-add.component.html',
  styleUrls: ['./employee-dialog-add.component.css']
})

export class EmployeeDialogAddComponent implements OnInit {

  employee: Employee;
  constructor(private route: ActivatedRoute, private router: Router,
              private employeeService: EmployeeService, public modal: NgbActiveModal) {
      this.employee = new Employee();
    }

  onSubmit() {
    this.employeeService.save(this.employee).subscribe(data => {
      this.modal.close('Ok click');
      console.log('welp');
    });
  }

  ngOnInit() {
  }

}
