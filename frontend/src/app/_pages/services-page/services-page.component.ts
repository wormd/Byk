import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_service/auth.service';
import { ServiceService } from 'src/app/_service/service.service';
import { TaskService } from 'src/app/_service/task.service';

@Component({
  selector: 'app-services-page',
  templateUrl: './services-page.component.html',
  styleUrls: ['./services-page.component.css']
})
export class ServicesPageComponent implements OnInit {

  loading: any;
  lock = true;

  constructor(private router: Router, private authService: AuthService, public serviceService: ServiceService) { }

  ngOnInit() {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    this.serviceService.update();
  }

  toggleActive(service) {
    if (!this.lock) {this.serviceService.toggleActive(service.id);
      service.active ? service.active = false : service.active = true;
    }
  }

  delete(service) {
    if (!this.lock) this.serviceService.delete(service.id);
  }
}
