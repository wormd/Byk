import { Component, OnInit } from '@angular/core';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { Task } from 'src/app/_model/task';
import { TasksService } from 'src/app/_service/tasks.service';

@Component({
  selector: 'app-expired-reminders',
  templateUrl: './expired-tasks.component.html',
  styleUrls: ['./expired-tasks.component.css']
})
export class ExpiredTasksComponent implements OnInit {

  reminders$: Observable<Task[]>;

  constructor(public reminderService: TasksService) { }

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
