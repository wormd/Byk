import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Reminder } from 'src/app/_model/reminder';
import { AlertService } from 'src/app/_service/alert.service';
import { RemindersService } from 'src/app/_service/reminders.service';
import { RemindersDialogEditComponent } from '../reminders-dialog-edit/reminders-dialog-edit.component';

@Component({
  selector: 'app-reminders-page-list',
  templateUrl: './reminders-page-list.component.html',
  styleUrls: ['./reminders-page-list.component.css']
})
export class RemindersPageListComponent implements OnInit {

  @Input() obs: Observable<Reminder[]>;
  list: Reminder[];
  loading = false;
  today = Date.now();
  timeleft: any;

  constructor(private modalService: NgbModal, public reminderService: RemindersService,
    private alertService: AlertService) { }

  ngOnInit(): void {
    this.obs.subscribe(d=>this.list = d);
  }

  editDialog(reminder) {
    const editComp = this.modalService.open(RemindersDialogEditComponent);
    editComp.componentInstance.input = reminder;
  }

}
