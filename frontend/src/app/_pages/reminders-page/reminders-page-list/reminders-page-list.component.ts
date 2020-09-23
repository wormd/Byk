import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Reminder } from 'src/app/_model/reminder';

@Component({
  selector: 'app-reminders-page-list',
  templateUrl: './reminders-page-list.component.html',
  styleUrls: ['./reminders-page-list.component.css']
})
export class RemindersPageListComponent implements OnInit {

  @Input() obs: Observable<Reminder[]>;
  loading = false;
  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  editDialog(id: string) {
    // TODO Implement
  }
    
  delete(id: string) {
    // TODO Implement
  }

}
