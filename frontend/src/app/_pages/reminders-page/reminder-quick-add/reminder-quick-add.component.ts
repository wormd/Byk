import { Component, OnInit } from '@angular/core';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Reminder } from 'src/app/_model/reminder';
import { AlertService } from 'src/app/_service/alert.service';
import { RemindersService } from 'src/app/_service/reminders.service';

@Component({
  selector: 'app-reminder-quick-add',
  templateUrl: './reminder-quick-add.component.html',
  styleUrls: ['./reminder-quick-add.component.css']
})
export class ReminderQuickAddComponent implements OnInit {

  dateModel: NgbDateStruct;
  reminder = new Reminder();
  cycle = false;

  constructor(public alertService: AlertService, private calendar: NgbCalendar,
    private reminderService: RemindersService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.reminder.due = new Date(Date.UTC(this.dateModel.year, this.dateModel.month - 1, this.dateModel.day));
    this.reminderService.add(this.reminder).then(() => this.reminder = new Reminder());
  }
}
