import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Task } from 'src/app/_model/task';
import { AlertService } from 'src/app/_service/alert.service';
import { TaskService } from 'src/app/_service/task.service';
import { TasksDialogEditComponent } from '../task-dialog-edit/tasks-dialog-edit.component';

@Component({
  selector: 'app-tasks-page-list',
  templateUrl: './tasks-page-list.component.html',
  styleUrls: ['./tasks-page-list.component.css']
})
export class TasksPageListComponent implements OnInit {

  @Input() obs: Observable<Task[]>;
  list: Task[];
  loading = false;
  today = Date.now();
  timeleft: any;

  constructor(private modalService: NgbModal, public reminderService: TaskService,
    private alertService: AlertService) { }

  ngOnInit(): void {
    this.obs.subscribe(d=>this.list = d);
  }

  editDialog(reminder) {
    const editComp = this.modalService.open(TasksDialogEditComponent);
    editComp.componentInstance.input = reminder;
  }

}
