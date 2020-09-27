import { Component, OnInit } from '@angular/core';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { Reminder } from 'src/app/_model/reminder';
import { RemindersService } from 'src/app/_service/reminders.service';

@Component({
  selector: 'app-expired-reminders',
  templateUrl: './expired-reminders.component.html',
  styleUrls: ['./expired-reminders.component.css']
})
export class ExpiredRemindersComponent implements OnInit {

  reminders$: Observable<Reminder[]>;

  constructor(public reminderService: RemindersService) { }

  ngOnInit(): void {
    this.fetchData();
  }

  setDone(id: string, descr: string) {
    this.reminderService.done(id, descr).then(d => {
      this.fetchData();
    });
  }

  fetchData() {
    this.reminders$ = this.reminderService.fetch({afters:0, done:false});
  }
}
