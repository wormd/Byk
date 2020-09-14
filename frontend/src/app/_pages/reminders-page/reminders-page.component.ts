import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from 'src/app/_service/auth.service';
import { RemindersService } from 'src/app/_service/reminders.service';
import { Reminder } from 'src/app/_model/reminder';

@Component({
  selector: 'app-reminders-page',
  templateUrl: './reminders-page.component.html',
  styleUrls: ['./reminders-page.component.css']
})

export class RemindersPageComponent implements OnInit {

  reminders: Reminder[];

  constructor(private router: Router, private authService: AuthService, 
    private modalService: NgbModal, private reminderService: RemindersService) { }

  ngOnInit() {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    this.reminderService.update()
    this.reminderService.reminders$.subscribe(d => {
      this.reminders = d;
    });
  }

  editDialog(id: string) {
// TODO Implement
  }

  delete(id: string) {
// TODO Implement
  }
}
