import {Component, OnInit} from '@angular/core';
import {AccountService} from '../_service/account.service';
import {Account} from '../_model/account';
import {AccountFilterService} from '../_service/account-filter.service';
import {Location} from '@angular/common';
import { AuthService } from '../_service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accounts-list',
  templateUrl: './accounts-list.component.html',
  styleUrls: ['./accounts-list.component.css']
})
export class AccountsListComponent implements OnInit {
  accounts: Account[];
  filterService = new AccountFilterService();

  starredItems: number[];

  constructor(private accountService: AccountService, private location: Location, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    if (!this.authService.loggedIn()) {
      this.router.navigate(['/login']);
    }
    // this.location.go('/accounts');
    this.starredItems = JSON.parse(localStorage.getItem('starred'));
    if (!this.starredItems) {
      this.starredItems = [];
    }
    this.accountService.update();
    this.accountService.accounts$.subscribe(data => {
      this.filterService.set(data);
      this.accounts = data;
    });
  }

  starClick(id: string) {
    if (this.starredItems.includes(+id)) {
      this.starredItems = this.starredItems.filter(e => +e !== +id);
    } else {
      this.starredItems.push(+id);
    }
    localStorage.setItem('starred', JSON.stringify(this.starredItems));
  }

  filterText(value) {
    this.accounts = this.filterService.filterText(value);
  }

  refreshTotal(id: string) {
    this.accountService.refreshTotal(id);
  }
}
