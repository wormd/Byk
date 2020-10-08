import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from 'src/app/_service/auth.service';
import { TasksService } from 'src/app/_service/tasks.service';
import { Task } from 'src/app/_model/task';

@Component({
  selector: 'app-reminders-page',
  templateUrl: './tasks-page.component.html',
  styleUrls: ['./tasks-page.component.css']
})

export class TasksPageComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService,
    public reminderService: TasksService) { }

  ngOnInit() {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    this.reminderService.update()
  }
}
