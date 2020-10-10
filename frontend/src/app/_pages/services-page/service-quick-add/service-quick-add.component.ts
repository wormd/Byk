import { Component, OnInit } from '@angular/core';
import { Service } from 'src/app/_model/service';
import { ServiceService } from 'src/app/_service/service.service';

@Component({
  selector: 'app-service-quick-add',
  templateUrl: './service-quick-add.component.html',
  styleUrls: ['./service-quick-add.component.css']
})
export class ServiceQuickAddComponent implements OnInit {

  service = new Service();

  constructor(private serviceService: ServiceService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.serviceService.add(this.service);
  }

}
