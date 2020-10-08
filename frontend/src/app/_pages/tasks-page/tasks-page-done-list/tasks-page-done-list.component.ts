import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Task } from 'src/app/_model/task';
import { TasksService } from 'src/app/_service/tasks.service';

@Component({
  selector: 'app-reminders-page-done-list',
  templateUrl: './tasks-page-done-list.component.html',
  styleUrls: ['./tasks-page-done-list.component.css']
})
export class TasksPageDoneListComponent implements OnInit {

  @Input() obs: Observable<Task[]>;
  loading = false;
  today = Date.now();
  timeleft: any;

  constructor(private modalService: NgbModal, public reminderService: TasksService) { }

  ngOnInit(): void {
  }

}
