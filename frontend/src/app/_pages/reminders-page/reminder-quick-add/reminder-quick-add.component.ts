import { Component, OnInit } from '@angular/core';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Reminder } from 'src/app/_model/reminder';
import { AlertService } from 'src/app/_service/alert.service';
import { RemindersService } from 'src/app/_service/reminders.service';

export const periods = [
  {value:604800,desc:'1 week'},
  {value:1209600,desc:'2 week'},
  {value:1813200,desc:'3 week'},
  {value:2592000,desc:'1 month'},
  {value:5184000,desc:'2 months'},
  {value:7776000,desc:'3 months'},
]

@Component({
  selector: 'app-reminder-quick-add',
  templateUrl: './reminder-quick-add.component.html',
  styleUrls: ['./reminder-quick-add.component.css']
})
export class ReminderQuickAddComponent implements OnInit {

  dateModel: NgbDateStruct;
  reminder = new Reminder();
  periods = periods;

  constructor(public alertService: AlertService, private calendar: NgbCalendar,
    private reminderService: RemindersService) {

    }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.dateModel) {
      this.reminder.dueBy = new Date(Date.UTC(this.dateModel.year, this.dateModel.month - 1, this.dateModel.day));
      if (!this.reminder.cycle) {
        this.reminder.cycletime = null;
      }
      this.reminderService.add(this.reminder).then(() => this.reminder = new Reminder());
    } else {
      this.alertService.message("Can't submit without a due date.", "danger");
    }
  }
}
