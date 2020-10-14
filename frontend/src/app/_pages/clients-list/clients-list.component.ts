import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Client } from 'src/app/_model/client';
import { ServiceService } from 'src/app/_service/service.service';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {

  @Input() clients: Client[];
  @Input() lock: Boolean;
  @Output() rem = new EventEmitter<string>();
  loading: any;

  constructor(private serviceService: ServiceService) { }

  ngOnInit(): void {
  }

  remove(clid: string) {
    if (!this.lock) {
      this.rem.emit(clid);
    }
  }
}
