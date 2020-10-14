import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Employee } from 'src/app/_model/employee';
import { ServiceService } from 'src/app/_service/service.service';

@Component({
  selector: 'app-staff-list',
  templateUrl: './staff-list.component.html',
  styleUrls: ['./staff-list.component.css']
})
export class StaffListComponent implements OnInit {

  @Input() staff: Employee[];
  @Input() lock: Boolean;
  @Output() rem = new EventEmitter<string>();


  loading: any;

  constructor(private serviceService: ServiceService) { }

  ngOnInit(): void {
  }

  remove(empid: string) {
    if (!this.lock) {
      this.rem.emit(empid);
    }
  }
}
