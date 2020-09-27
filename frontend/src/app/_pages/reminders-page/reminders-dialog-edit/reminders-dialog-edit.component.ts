import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Reminder } from 'src/app/_model/reminder';
import { RemindersService } from 'src/app/_service/reminders.service';
import { periods } from '../reminder-quick-add/reminder-quick-add.component';

@Component({
  selector: 'app-reminders-dialog-edit',
  templateUrl: './reminders-dialog-edit.component.html',
  styleUrls: ['./reminders-dialog-edit.component.css']
})
export class RemindersDialogEditComponent implements OnInit {

  @Input() input: Reminder;
  reminder: Reminder;
  dueByModel: NgbDateStruct;
  periods = periods;

  constructor(private reminderService: RemindersService, public modal: NgbActiveModal) { 
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
