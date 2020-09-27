import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Reminder } from 'src/app/_model/reminder';
import { RemindersService } from 'src/app/_service/reminders.service';

@Component({
  selector: 'app-reminders-page-done-list',
  templateUrl: './reminders-page-done-list.component.html',
  styleUrls: ['./reminders-page-done-list.component.css']
})
export class RemindersPageDoneListComponent implements OnInit {

  @Input() obs: Observable<Reminder[]>;
  loading = false;
  today = Date.now();
  timeleft: any;

  constructor(private modalService: NgbModal, public reminderService: RemindersService) { }

  ngOnInit(): void {
  }

}
