import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Supply } from 'src/app/_model/supply';
import { ServiceService } from 'src/app/_service/service.service';

@Component({
  selector: 'app-supplies-list',
  templateUrl: './supplies-list.component.html',
  styleUrls: ['./supplies-list.component.css']
})
export class SuppliesListComponent implements OnInit {

  @Input() supplies: Supply[];
  @Input() lock: Boolean;
  @Output() rem = new EventEmitter<string>();
  loading: any;

  constructor(private serviceService: ServiceService) { }

  ngOnInit(): void {
  }

  remove(suppid: string) {
    if (!this.lock) {
      this.rem.emit(suppid);
    }
  }

}
