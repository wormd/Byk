import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, filter, map } from 'rxjs/operators';

@Component({
  selector: 'app-add-to-list',
  templateUrl: './add-to-list.component.html',
  styleUrls: ['./add-to-list.component.css']
})
export class AddToListComponent implements OnInit {

  message: string;

  @Input() list: any[];
  @Output() selected = new EventEmitter();

  model: any;

  formatter = (object: any) => object.name+' '+object.surname;

  objectSearch = (text$: Observable<string>) => text$.pipe(
    debounceTime(200),
    distinctUntilChanged(),
    filter(term => term.length >= 1),
    map(term => this.list.filter(item => new RegExp(term, 'mi').test(item.name+' '+item.surname)).slice(0, 10)))

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  changed(event) {
    this.message = "Added `"+event.item.name+' '+event.item?.surname+"` to list";
    this.selected.emit(event.item);
  }
}
