import { Component, Input, OnInit } from '@angular/core';
import { Supply } from 'src/app/_model/supply';

@Component({
  selector: 'app-supplies-list',
  templateUrl: './supplies-list.component.html',
  styleUrls: ['./supplies-list.component.css']
})
export class SuppliesListComponent implements OnInit {

  @Input() supplies: Supply[];
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
