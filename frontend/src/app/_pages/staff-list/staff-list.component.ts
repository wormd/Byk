import { Component, Input, OnInit } from '@angular/core';
import { Employee } from 'src/app/_model/employee';
import { Service } from 'src/app/_model/service';

@Component({
  selector: 'app-staff-list',
  templateUrl: './staff-list.component.html',
  styleUrls: ['./staff-list.component.css']
})
export class StaffListComponent implements OnInit {

  @Input() staff: Employee[];
  @Input() lock: Boolean;

  loading: any;

  constructor() { }

  ngOnInit(): void {
  }

  remove(id: string) {
    if (!this.lock) {
      // implement
    }
  }

}
