import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeesListPageComponent} from './_pages/employees-list-page/employees-list-page.component';
import {AccountPageComponent} from './_pages/accounts-list-page/account-page/account-page.component';
import {LoginFormPageComponent} from './_pages/login-form-page/login-form-page.component';
import {HomepageComponent} from './_pages/homepage/homepage.component';
import {AccountsListPageComponent} from './_pages/accounts-list-page/accounts-list-page.component';
import {TransactionsPageComponent} from './_pages/transactions-page/transactions-page.component';
import {CashbookPageComponent} from './_pages/cashbook-page/cashbook-page.component';
import { SignupFormPageComponent } from './_pages/signup-form-page/signup-form-page.component';
import { EmployeeFormComponent } from './_pages/employees-list-page/employee-form/employee-form.component';
import { RemindersPageComponent } from './_pages/reminders-page/reminders-page.component';
import { InvoicesPageComponent } from './_pages/invoices-page/invoices-page.component';
import { SuppliesPageComponent } from './_pages/supplies-page/supplies-page.component';


const routes: Routes = [
  { path: 'employees', component: EmployeesListPageComponent},
  { path: 'employee-add', component: EmployeeFormComponent},
  { path: 'cashbook', component: CashbookPageComponent},
  { path: 'account', component: AccountPageComponent},
  { path: 'account/:id', component: AccountPageComponent},
  { path: 'login', component: LoginFormPageComponent },
  { path: 'accounts', component: AccountsListPageComponent},
  { path: '', component: HomepageComponent },
  { path: 'transactions', component: TransactionsPageComponent },
  { path: 'signup', component: SignupFormPageComponent },
  { path: 'reminders', component: RemindersPageComponent },
  { path: 'invoices', component: InvoicesPageComponent },
  { path: 'supplies', component: SuppliesPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
