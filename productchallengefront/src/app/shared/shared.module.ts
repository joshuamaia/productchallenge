import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BreadCrumbComponent } from './components/bread-crumb/bread-crumb.component';
import { PageHeaderComponent } from './components/page-header/page-header.component';
import { FormFieldErrorComponent } from './components/form-field-error/form-field-error.component';
import { ServerErrorMessagesComponent } from './components/server-error-messages/server-error-messages.component';
import { PaginatorModule } from 'primeng/paginator';
import { NgSelectModule } from '@ng-select/ng-select';
import {
  FaIconLibrary,
  FontAwesomeModule,
} from '@fortawesome/angular-fontawesome';
import { fontAwesomeIcons } from '../pages/config/font-awesome-icons';
import { KpiComponent } from './components/kpi/kpi.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    PaginatorModule,
    NgSelectModule,
    FormsModule,
    FontAwesomeModule,
  ],
  declarations: [
    BreadCrumbComponent,
    PageHeaderComponent,
    FormFieldErrorComponent,
    ServerErrorMessagesComponent,
    KpiComponent,
  ],
  exports: [
    // shared modules
    CommonModule,
    ReactiveFormsModule,
    RouterModule,

    // shared components
    BreadCrumbComponent,
    PageHeaderComponent,
    FormFieldErrorComponent,
    ServerErrorMessagesComponent,
    KpiComponent,
    PaginatorModule,
    NgSelectModule,
    FormsModule,
    FontAwesomeModule,
  ],
})
export class SharedModule {
  constructor(private fontAwesomeLibrary: FaIconLibrary) {
    this.configureFontAwesome();
  }

  private configureFontAwesome() {
    this.fontAwesomeLibrary.addIcons(...fontAwesomeIcons);
  }
}
