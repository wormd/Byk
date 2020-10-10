import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';

import {EmployeesListPageComponent} from './_pages/employees-list-page/employees-list-page.component';
import {EmployeeDialogAddComponent} from './_pages/employees-list-page/employee-dialog-add/employee-dialog-add.component';
import {EmployeeDialogEditComponent} from './_pages/employees-list-page/employee-dialog-edit/employee-dialog-edit.component';
import {AccountTransListComponent} from './_pages/accounts-list-page/account-page/account-trans-list/account-trans-list.component';
import {AccountListComponent} from './tools-nav/account-list/account-list.component';
import {TransactionFormComponent} from './tools-nav/transaction-form/transaction-form.component';
import {AccountPageComponent} from './_pages/accounts-list-page/account-page/account-page.component';
import {AccountFormComponent} from './tools-nav/account-form/account-form.component';
import {LoginFormPageComponent} from './_pages/login-form-page/login-form-page.component';
import {JwtInterceptor} from './_interceptor/jwt.interceptor';
import {HomepageComponent} from './_pages/homepage/homepage.component';
import {PaginationComponent} from './_util/pagination/pagination.component';
import {ConfirmDialogComponent} from './_util/confirm-dialog/confirm-dialog.component';
import {AccountsListPageComponent} from './_pages/accounts-list-page/accounts-list-page.component';
import {CustomCheckboxDirective} from './_directive/custom-checkbox.directive';
import {AlertMessageComponent} from './_util/alert-message/alert-message.component';
import {TransactionsPageComponent} from './_pages/transactions-page/transactions-page.component';
import {MonthBarComponent} from './_util/month-bar.component';
import {YearBarComponent} from './_util/year-bar.component';
import {AccountSelectorComponent} from './_util/account-selector.component';
import {BeautifyNumPipe} from './_pipe/beautify-num.pipe';
import {CashbookPageComponent} from './_pages/cashbook-page/cashbook-page.component';
import { SignupFormPageComponent } from './_pages/signup-form-page/signup-form-page.component';
import { TasksPageComponent } from './_pages/tasks-page/tasks-page.component';
import { InvoicesPageComponent } from './_pages/invoices-page/invoices-page.component';
import { SuppliesPageComponent } from './_pages/supplies-page/supplies-page.component';
import { TasksQuickAddComponent } from './_pages/tasks-page/task-quick-add/tasks-quick-add.component';
import { SToStringPipe } from './_pipe/s-to-string.pipe';
import { TasksPageListComponent } from './_pages/tasks-page/tasks-page-list/tasks-page-list.component';
import { ToUnixtPipe } from './_pipe/to-unixt.pipe';
import { ExpiredPipe } from './_pipe/expired.pipe';
import { VarDirective } from './_directive/var.directive';
import { TasksPageDoneListComponent } from './_pages/tasks-page/tasks-page-done-list/tasks-page-done-list.component';
import { TasksDialogEditComponent } from './_pages/tasks-page/task-dialog-edit/tasks-dialog-edit.component';
import { PinnedAccountsComponent } from './_pages/homepage/pinned-accounts/pinned-accounts.component';
import { ExpiredTasksComponent } from './_pages/homepage/expired-tasks/expired-tasks.component';
import { ServicesPageComponent } from './_pages/services-page/services-page.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeesListPageComponent,
    EmployeeDialogAddComponent,
    CashbookPageComponent,
    AccountPageComponent,
    AccountListComponent,
    AccountTransListComponent,
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
    TasksPageComponent,
    InvoicesPageComponent,
    SuppliesPageComponent,
    EmployeeDialogEditComponent,
    TasksQuickAddComponent,
    SToStringPipe,
    TasksPageListComponent,
    ToUnixtPipe,
    ExpiredPipe,
    VarDirective,
    TasksPageDoneListComponent,
    TasksDialogEditComponent,
    PinnedAccountsComponent,
    ExpiredTasksComponent,
    ServicesPageComponent,
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
