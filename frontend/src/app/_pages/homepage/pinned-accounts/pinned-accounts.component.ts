import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/_service/account.service';
import { Account } from 'src/app/_model/account';

@Component({
  selector: 'app-pinned-accounts',
  templateUrl: './pinned-accounts.component.html',
  styleUrls: ['./pinned-accounts.component.css']
})
export class PinnedAccountsComponent implements OnInit {

  public prefs: Account[];

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    const starred: string[] = JSON.parse(localStorage.getItem('starred'));
    if (starred) {
      this.accountService.get(starred).then(d => {
        this.prefs = d;
      });
    }
  }

}
