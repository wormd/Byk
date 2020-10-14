import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgbActiveModal, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Supply } from 'src/app/_model/supply';
import { SupplyService } from 'src/app/_service/supply.service';

@Component({
  selector: 'app-supply-dialog-add',
  templateUrl: './supply-dialog-add.component.html',
  styleUrls: ['./supply-dialog-add.component.css']
})
export class SupplyDialogAddComponent implements OnInit {

  check = false;
  supply: Supply;
  dateModel: NgbDateStruct;
  days: number;
  @Output() selected = new EventEmitter();

  constructor(public modal: NgbActiveModal, private supplyService: SupplyService) { 
    this.supply = new Supply();
  }

  ngOnInit(): void {

  }
  

  onSubmit() {
    this.dateModel ? this.supply.payDate = new Date(Date.UTC(this.dateModel.year, this.dateModel.month - 1, this.dateModel.day)) : this.supply.payDate = new Date();
    this.check ? this.supply.payEverys = this.days * 86400 : this.supply.payEverys = null;
    this.supplyService.add(this.supply).then(data => {
      this.selected.emit(data);
      this.modal.close('Ok click');
    });
  }

}
