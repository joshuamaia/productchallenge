import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from './shared/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService, private router: Router) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (!this.authService.isLoggedIn() && !request.url.includes('login')) {
      this.router.navigate(['/login']);
      return new Observable<HttpEvent<any>>(); // Retorna um Observable vazio para interromper a requisição
    }
    const authToken = this.authService.getToken();
    if (authToken) {
      // Clona a requisição e adiciona o cabeçalho de autenticação
      const authReq = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${authToken}`),
      });
      return next.handle(authReq).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 401 || error.status === 403) {
            this.authService.logout();
          }
          return throwError(() => error);
        })
      );
    }
    return next.handle(request);
  }
}
