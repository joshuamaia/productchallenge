import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopoComponent } from './layout/topo/topo.component';
import { RodapeComponent } from './layout/rodape/rodape.component';
import { DashboardComponent } from './layout/dashboard/dashboard.component';
import { MenuComponent } from './layout/menu/menu.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { SharedModule } from './shared/shared.module';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { AuthInterceptor } from './pages/login/auth.interceptor';
import { AuthService } from './pages/login/shared/auth.service';
import { LoginModule } from './pages/login/login.module';

@NgModule({
  declarations: [
    AppComponent,
    TopoComponent,
    RodapeComponent,
    DashboardComponent,
    MenuComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgHttpLoaderModule.forRoot(),
    SharedModule,
    LoginModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
