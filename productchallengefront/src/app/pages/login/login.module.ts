import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [LoginScreenComponent],
  imports: [CommonModule, LoginRoutingModule, SharedModule],
  exports: [LoginScreenComponent],
})
export class LoginModule {}
