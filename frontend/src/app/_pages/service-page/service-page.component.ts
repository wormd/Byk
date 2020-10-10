import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Service } from 'src/app/_model/service';
import { AuthService } from 'src/app/_service/auth.service';
import { ServiceService } from 'src/app/_service/service.service';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.css']
})
export class ServicePageComponent implements OnInit {

  service$: Observable<Service>;

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthService, public serviceService: ServiceService) { }

  ngOnInit(): void {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    this.service$ = this.serviceService.get(this.route.snapshot.paramMap.get('id'))
  }
}
