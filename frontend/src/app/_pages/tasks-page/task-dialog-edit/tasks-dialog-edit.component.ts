import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Task } from 'src/app/_model/task';
import { TaskService } from 'src/app/_service/task.service';
import { periods } from '../task-quick-add/tasks-quick-add.component';

@Component({
  selector: 'app-reminders-dialog-edit',
  templateUrl: './tasks-dialog-edit.component.html',
  styleUrls: ['./tasks-dialog-edit.component.css']
})
export class TasksDialogEditComponent implements OnInit {

  @Input() input: Task;
  reminder: Task;
  dueByModel: NgbDateStruct;
  periods = periods;

  constructor(private reminderService: TaskService, public modal: NgbActiveModal) {
  }

  ngOnInit(): void {
    this.reminder = Object.assign({}, this.input);
  }

  onSubmit() {
    if (this.dueByModel) {
      this.reminder.dueBy = new Date(this.dueByModel.year, this.dueByModel.month - 1, this.dueByModel.day);
    }

    this.reminderService.edit(this.reminder).then(data => {
      this.modal.close('submit');
    });
  }

}
