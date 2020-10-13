import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/_model/client';
import { Service } from 'src/app/_model/service';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {

  @Input() clients: Client[];
  @Input() lock: Boolean;
  loading: any;

  constructor() { }

  ngOnInit(): void {
  }

  remove(id: string) {
    if (!this.lock) {
    // implement
    }
  }

}
