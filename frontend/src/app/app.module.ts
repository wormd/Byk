import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';

import {EmployeesListPageComponent} from './_pages/employees-list-page/employees-list-page.component';
import {EmployeeFormComponent} from './_pages/employees-list-page/employee-form/employee-form.component';
import {TransactionsListComponent} from './_pages/transactions-page/transactions-list/transactions-list.component';
import {AccountListComponent} from './tools-nav/account-list/account-list.component';
import {TransactionFormComponent} from './tools-nav/transaction-form/transaction-form.component';
import {AccountPageComponent} from './_pages/accounts-list-page/account-page/account-page.component';
import {AccountFormComponent} from './tools-nav/account-form/account-form.component';
import {LoginFormPageComponent} from './_pages/login-form-page/login-form-page.component';
import {JwtInterceptor} from './_interceptor/jwt.interceptor';
import {HomepageComponent} from './_pages/homepage/homepage.component';
import {PaginationComponent} from './_util/pagination/pagination.component';
import {ConfirmDialogComponent} from './confirm-dialog/confirm-dialog.component';
import {AccountsListPageComponent} from './_pages/accounts-list-page/accounts-list-page.component';
import {CustomCheckboxDirective} from './_directive/custom-checkbox.directive';
import {AlertMessageComponent} from './alert-message/alert-message.component';
import {TransactionsPageComponent} from './_pages/transactions-page/transactions-page.component';
import {MonthBarComponent} from './_util/month-bar.component';
import {YearBarComponent} from './_util/year-bar.component';
import {AccountSelectorComponent} from './_util/account-selector.component';
import {BeautifyNumPipe} from './_pipe/beautify-num.pipe';
import {CashbookPageComponent} from './_pages/cashbook-page/cashbook-page.component';
import { SignupFormPageComponent } from './_pages/signup-form-page/signup-form-page.component';
import { RemindersPageComponent } from './_pages/reminders-page/reminders-page.component';
import { InvoicesPageComponent } from './_pages/invoices-page/invoices-page.component';
import { SuppliesPageComponent } from './_pages/supplies-page/supplies-page.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeesListPageComponent,
    EmployeeFormComponent,
    CashbookPageComponent,
    AccountPageComponent,
    AccountListComponent,
    TransactionsListComponent,
    TransactionFormComponent,
    AccountFormComponent,
    LoginFormPageComponent,
    HomepageComponent,
    PaginationComponent,
    ConfirmDialogComponent,
    AccountsListPageComponent,
    CustomCheckboxDirective,
    AlertMessageComponent,
    TransactionsPageComponent,
    MonthBarComponent,
    YearBarComponent,
    AccountSelectorComponent,
    BeautifyNumPipe,
    SignupFormPageComponent,
    RemindersPageComponent,
    InvoicesPageComponent,
    SuppliesPageComponent,
  ],
  entryComponents: [
    ConfirmDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent],
})
export class AppModule { }
