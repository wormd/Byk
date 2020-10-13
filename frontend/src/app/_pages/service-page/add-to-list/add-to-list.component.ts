import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NgbActiveModal, NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { merge, Observable, Subject } from 'rxjs';
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

  @ViewChild('instance', {static: true}) instance: NgbTypeahead;
  focus$ = new Subject<string>();
  click$ = new Subject<string>();

  strings = (object: any) => {
    if (object.surname) {
      return object.name+' '+object.surname;
    } else {
      return object.name;
    }
  }

  search = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
    const clicksWithClosedPopup$ = this.click$.pipe(filter(() => !this.instance.isPopupOpen()));
    const inputFocus$ = this.focus$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term => (term === ''  ? this.list
        : this.list.filter(item => new RegExp(term, 'mi').test(this.strings(item))).slice(0, 10))));
  }

  formatter = (object: any) => this.strings(object);

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void { }

  changed(event) {
    this.message = "Added `"+this.strings(event.item)+"` to list";
    this.selected.emit(event.item);
  }
}
